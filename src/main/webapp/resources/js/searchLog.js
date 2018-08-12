/**
 * Obtaining a list of file paths with a specific extension,
 * if the extension is not specified by default, log.
 * @param searchText search text in the file
 * @param extension extension file
 * @param rootPath root of the folder where the search begins
 * @return {Promise<Response>}
 */
const getFilePaths = (searchText, extension, rootPath) => {
    return fetch(`http://localhost:8080/search?query=${searchText}&rootPath=${rootPath}&extension=${extension}`);
};

/**
 * Function to find file paths
 */
const searchLog = () => {
    let searchText = document.getElementById('search-text').value;
    let rootPath = document.getElementById('root-directory').value;
    let extension = document.getElementById('extension').value.replace(/\s+/g, '');

    if (Object.is(extension, "")) {
        extension = "log";
    }

    getFilePaths(searchText, extension, rootPath)
        .then(resp => {
            resp.json().then(paths => {
                let listFilePaths = getTree(paths);
                const container = document.getElementById('container');
                createTree(container, listFilePaths);

            })
        })
        .catch(err => console.log(err));
};


