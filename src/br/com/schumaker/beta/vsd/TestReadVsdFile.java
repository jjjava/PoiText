package br.com.schumaker.beta.vsd;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.apache.poi.POIOLE2TextExtractor;
import org.apache.poi.hdgf.HDGFDiagram;
import org.apache.poi.hdgf.chunks.Chunk.Command;
import org.apache.poi.hdgf.streams.ChunkStream;
import org.apache.poi.hdgf.streams.PointerContainingStream;
import org.apache.poi.hsmf.datatypes.Chunk;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author hudson schumaker
 */
public class TestReadVsdFile {

    public final class VisioTextExtractor extends POIOLE2TextExtractor {

        private HDGFDiagram hdgf;

        public VisioTextExtractor(HDGFDiagram hdgf) {
            super(hdgf);
            this.hdgf = hdgf;
        }

        public VisioTextExtractor(POIFSFileSystem fs) throws IOException {
            this(fs.getRoot());
        }

        public VisioTextExtractor(NPOIFSFileSystem fs) throws IOException {
            this(fs.getRoot());
        }

        public VisioTextExtractor(DirectoryNode dir) throws IOException {
            this(new HDGFDiagram(dir));
        }

        /**
         * @deprecated Use {@link #VisioTextExtractor(DirectoryNode)} instead
         */
        @Deprecated
        public VisioTextExtractor(DirectoryNode dir, POIFSFileSystem fs) throws IOException {
            this(new HDGFDiagram(dir, fs));
        }

        public VisioTextExtractor(InputStream inp) throws IOException {
            this(new NPOIFSFileSystem(inp));
        }

        /**
         * Locates all the text entries in the file, and returns their contents.
         */
        public String[] getAllText() {
            ArrayList<String> text = new ArrayList<String>();
            for (int i = 0; i < hdgf.getTopLevelStreams().length; i++) {
                findText((Stream) hdgf.getTopLevelStreams()[i], text);
            }
            return text.toArray(new String[text.size()]);
        }

        private void findText(Stream stream, ArrayList<String> text) {
            if (stream instanceof PointerContainingStream) {
                PointerContainingStream ps = (PointerContainingStream) stream;
                for (int i = 0; i < ps.getPointedToStreams().length; i++) {
                    findText((Stream) ps.getPointedToStreams()[i], text);
                }
            }
            if (stream instanceof ChunkStream) {
                ChunkStream cs = (ChunkStream) stream;
                for (int i = 0; i < cs.getChunks().length; i++) {
                    Chunk chunk = cs.getChunks()[i];
                    if (chunk != null
                            && chunk.getName() != null
                            && chunk.getName().equals("Text")
                            && chunk.getCommands().length > 0) {

                        // First command
                        Command cmd = chunk.getCommands()[0];
                        if (cmd != null && cmd.getValue() != null) {
					   // Capture the text, as long as it isn't
                            //  simply an empty string
                            String str = cmd.getValue().toString();
                            if (str.equals("") || str.equals("\n")) {
                                // Ignore empty strings
                            } else {
                                text.add(str);
                            }
                        }
                    }
                }
            }
        }

        /**
         * Returns the textual contents of the file. Each textual object's text
         * will be separated by a newline
         */
        public String getText() {
            StringBuffer text = new StringBuffer();
            String[] allText = getAllText();
            for (int i = 0; i < allText.length; i++) {
                text.append(allText[i]);
                if (!allText[i].endsWith("\r")
                        && !allText[i].endsWith("\n")) {
                    text.append("\n");
                }
            }
            return text.toString();
        }

    }
