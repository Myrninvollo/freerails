package freerails.server;

import java.io.File;
import java.io.FilenameFilter;

/**
 * A SavedGamesManager reads and writes gzipped saved games to the working
 * directory.
 */
class SavedGameFileFilter implements FilenameFilter {

    public boolean accept(File dir, String name) {
        return (name.endsWith(".sav"));
    }
}