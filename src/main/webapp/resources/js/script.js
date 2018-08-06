const getFilePaths = (searchText, rootDir,extension) => {
    return fetch(`http://localhost:8080/search?query=${searchText}&rootPath=${rootDir}&extension=${extension}`);
};

const search = () => {
    let searchText = document.getElementById('search-text').value;
    let rootPath = document.getElementById('root-directory').value;
    let extension = document.getElementById('extension').value;

    console.log('quering', searchText, extension, rootPath);

    getFilePaths(searchText, extension, rootPath)
        .then(resp => {
            resp.json().then(data => {
                //TODO: process server response
                // console.log('data', data.paths);
                // for(let i = 0; i < data.paths.length; i++) {
                //     console.log(data.paths[i]);
                // }
            })
        })
        .catch(err => console.log(err));
};

