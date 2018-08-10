const getFilePaths = (searchText, extension, rootPath) => {
    return fetch(`http://localhost:8080/search?query=${searchText}&rootPath=${rootPath}&extension=${extension}`);
};

let paths;
const search = () => {
    let searchText = document.getElementById('search-text').value;
    let rootPath = document.getElementById('root-directory').value;
    let extension = document.getElementById('extension').value.replace(/\s+/g, '');

    if (Object.is(extension, "")) {
        extension = "log";
    }

    console.log('quering', searchText, extension, rootPath);

    getFilePaths(searchText, extension, rootPath)
        .then(resp => {
            resp.json().then(data => {
                paths = data.paths;
                //TODO: process server response

                console.log(paths);
                let filess = getTree(paths);
                console.log(filess);

                const container = document.getElementById('container');
                createTree(container, filess);

            })
        })
        .catch(err => console.log(err));
};

