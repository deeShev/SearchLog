/**
 * Function to get a file tree
 * @param paths - array of paths
 * @return fileTree - file tree
 */
const getTree = (paths) => {
    let fileTree = {};
    let indexToArrayPath;

    if (paths instanceof Array === false) {
        throw new Error('An incorrect parameter is passed ' + paths + " , expected array of paths!");
    }

    /**
     * Forming the currentPath object
     * @param prevDir
     * @param currDir
     * @param i
     * @param currFilePath
     * @returns {*}
     */
    let mergePathsIntoFileTree = (prevDir, currDir, i, currFilePath) => {

        if (i === currFilePath.length - 1) {
            prevDir[currDir] = 'file';
            prevDir[currDir] = paths[indexToArrayPath];
        }

        if (!prevDir.hasOwnProperty(currDir)) {
            prevDir[currDir] = {};
        }

        return prevDir[currDir];
    };

    /**
     *  Converting a currentPath to an array and running the reduce() method to retrieve the object.
     *  /file1/file2 => [file1,file2]
     * @param filePath - currentPath to file
     * @returns string - file tree
     */
    let parseFilePath = (filePath) => {
        let fileLocation;
        /**
         * check for path in windows
         */
        if (filePath.charAt(0) !== "/"){
            fileLocation = filePath.split('\\');
        }else {
            fileLocation = filePath.split('/');
        }

        if (fileLocation.length === 1) {
            fileTree[fileLocation[0]] = 'file';
            fileTree[fileLocation[0]] = paths[indexToArrayPath];
            return fileTree;
        }

        fileLocation.reduce(mergePathsIntoFileTree, fileTree);
    };

    for (let i = 0; i < paths.length; i++) {
        indexToArrayPath = i;
        parseFilePath(paths[i]);
    }
    return fileTree;
};