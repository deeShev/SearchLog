/**
 * Сreate a file currentPath tree.
 * @param container - container in html file
 * @param objTree - object of paths
 */
const createTree = (container, objTree) => {
    if (objTree instanceof Object === false) {
        throw new Error("An incorrect parameter is passed " + objTree + " , expected object of paths!");
    }
    container.innerHTML = createTreeDom(objTree).innerHTML;
    addEventToFileTag();
};

let pathClick;
/**
 * Create a currentPath tree from the tags: ul and li
 * @param objTree object of paths
 * @return {*}
 */
const createTreeDom = (objTree) => {
    if (isObjectEmpty(objTree)) return;

    let ul = document.createElement('ul');
    ul.className = "container";

    for (let key in objTree) {
        let li = document.createElement('li');

        if (key === "") {
            li.innerHTML = "/";
        } else if (typeof(objTree[key]) === "string") {
            ul.className = "list_files";
            li.className = "file";
            li.setAttribute("path_to_file", objTree[key]);
            li.innerHTML = key;
        } else {
            li.innerHTML = key;
            li.className = "folder";
        }

        let childrenUl = createTreeDom(objTree[key]);

        if (childrenUl) {
            li.appendChild(childrenUl);
        }

        ul.appendChild(li);
    }

    pathClick = ul;
    return ul;
};

/**
 * Check for emptiness
 * @param obj - object of paths
 * @return {boolean}
 */
const isObjectEmpty = (obj) => {
    for (let key in obj) {
        if (typeof(obj) === "string") {
            return true;
        } else {
            return false;
        }
    }
    return true;
};

/**
 * Adding an Event to a File Node
 */
const addEventToFileTag = () => {
    let listFile = document.getElementsByClassName("file");
    [].forEach.call(listFile, (li) => {
        li.addEventListener('click', () => {
            pathClick = li.getAttribute("path_to_file");
            getContentPage(pathClick);
        })

    });
};

