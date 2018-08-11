/**
 * Сreate a file path tree.
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
 * Create a path tree from the tags: ul and li
 * @param objTree
 * @return {*}
 */
const createTreeDom = (objTree) => {
    if (isObjectEmpty(objTree)) return;

    let ul = document.createElement('ul');
    ul.className = "Container";

    for (let key in objTree) {
        let li = document.createElement('li');

        if (key === "") {
            li.innerHTML = "/";
        } else if (typeof(objTree[key]) === "string") {
            ul.className = "listFiles";
            li.className = "File";
            li.setAttribute("PathToFile", objTree[key]);
            li.innerHTML = key;
        } else {
            li.innerHTML = key;
            li.className = "Folder";
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
 * @param obj
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

const addEventToFileTag = () => {
    let listFile = document.getElementsByClassName("File");
    [].forEach.call(listFile, (li) => {
        li.addEventListener('click', () => {
            pathClick = li.getAttribute("PathToFile");
            alert(getPathClick())
        })

    });
};

const getPathClick = () => {
    return pathClick;
};
