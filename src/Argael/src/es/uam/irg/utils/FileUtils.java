/**
 * Copyright 2022
 * Andrés Segura-Tinoco
 * Information Retrieval Group at Universidad Autonoma de Madrid
 *
 * This is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * the current software. If not, see <http://www.gnu.org/licenses/>.
 */
package es.uam.irg.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import es.uam.irg.data.TreeNode;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yaml.snakeyaml.Yaml;

/**
 * Class with a set of static utility functions.
 */
public class FileUtils {

    /**
     *
     * @param directory
     * @return
     */
    public static boolean createDirectory(String directory) {
        File dir = new File(directory);
        boolean result = dir.exists();
        if (!result) {
            dir.mkdirs();
            result = dir.exists();
        }
        return result;
    }

    /**
     *
     * @param filePath
     * @return
     */
    public static String getDirectory(String filePath) {
        File file = new File(filePath);
        String directory = file.getAbsoluteFile().getParent();
        return directory;
    }

    /**
     *
     * @param filename
     * @return
     */
    public static String getFileExtension(String filename) {
        int index = filename.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return filename.substring(index + 1);
    }

    /**
     *
     * @param filename
     * @return
     */
    public static String getFilenameWithoutExt(String filename) {
        int index = filename.lastIndexOf(".");
        if (index == -1) {
            return filename;
        }
        return filename.substring(0, index);
    }

    /**
     *
     * @param filepath
     * @param withHeader
     * @return
     */
    public static List<String[]> readCsvFile(String filepath, boolean withHeader) {
        List<String[]> csvFile = new ArrayList<>();
        File file = new File(filepath);

        if (file.exists()) {
            try {
                InputStreamReader inputFile = new InputStreamReader(new FileInputStream(filepath), StandardCharsets.UTF_8);
                try ( CSVReader reader = new CSVReader(inputFile)) {
                    if (!withHeader) {
                        reader.readNext();
                    }
                    csvFile = reader.readAll();
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return csvFile;
    }

    /**
     *
     * @param filepath
     * @return
     */
    public static String readFile(String filepath) {
        Path filePath = Path.of(filepath);
        return readFile(filePath);
    }

    /**
     *
     * @param filepath
     * @return
     */
    public static String readFile(Path filepath) {
        String content = "";
        try {
            if (filepath.toFile().exists()) {
                content = Files.readString(filepath);
            }

        } catch (OutOfMemoryError | IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;
    }

    /**
     *
     * @param directory
     * @param fileExt
     * @return
     */
    public static List<String> readFilenamesInFolder(String directory, String fileExt) {
        List<String> fileNames = new ArrayList<>();
        File folder = new File(directory);
        String currName;
        String currExt;

        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                currName = file.getName();
                currExt = getFileExtension(currName);
                if (currExt.equals(fileExt)) {
                    fileNames.add(getFilenameWithoutExt(currName));
                }
            }
        }

        return fileNames;
    }

    /**
     *
     * @param directory
     * @return
     */
    public static TreeNode readPostHierarchy(String directory) {
        TreeNode root = null;
        Map<String, TreeNode> nodes = new HashMap<>();
        String filename = directory + "/comment_hierarchy.csv";
        System.out.println(filename);
        String header = "proposalId,commendId,parentId";
        String line;
        String csvSplitBy = ",";

        try ( BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
                if (!line.equals(header)) {
                    String[] data = line.split(csvSplitBy);

                    String categoryId = data[0];
                    String nodeId = data[1];
                    String parentId = data[2];
                    if (parentId.equals("-1")) {
                        parentId = categoryId;
                    }

                    TreeNode parentNode = nodes.get(parentId);
                    TreeNode childNode = nodes.get(nodeId);

                    if (parentNode == null) {
                        parentNode = new TreeNode(parentId, 0); // Root has depth 0
                        nodes.put(parentId, parentNode);
                    }

                    if (childNode == null) {
                        int parentDepth = parentNode.getDepth();
                        childNode = new TreeNode(nodeId, parentDepth + 1);
                        nodes.put(nodeId, childNode);
                    }

                    parentNode.addChild(childNode);

                    if (root == null) {
                        root = parentNode;
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return root;
    }

    /**
     *
     * @param filepath
     * @return
     */
    public static Map<String, Object> readYamlFile(String filepath) {
        Map<String, Object> data = null;

        try {
            // Get the file
            File file = new File(filepath);

            // Check if the specified file exists or not
            if (file.exists()) {
                InputStream inputStream = new FileInputStream(file);
                Yaml yaml = new Yaml();
                data = (Map<String, Object>) yaml.load(inputStream);
            }

        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    /**
     *
     * @param filepath
     * @param data
     * @return
     */
    public static boolean saveCsvFile(String filepath, List<String[]> data) {
        boolean result = false;

        try ( CSVWriter writer = new CSVWriter(new FileWriter(filepath, StandardCharsets.UTF_8, false))) {
            writer.writeAll(data);
            result = true;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     *
     * @param filepath
     * @param content
     * @return
     */
    public static boolean saveFile(String filepath, String content) {
        boolean result = false;
        try {
            if (!filepath.isEmpty() && !content.isEmpty()) {
                Files.write(Paths.get(filepath), content.getBytes());
                result = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
