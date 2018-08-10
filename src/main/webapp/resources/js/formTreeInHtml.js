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
        } else if (typeof(objTree[key]) !== "string") {
            li.innerHTML = key;
        }

        if (typeof(objTree[key]) === "string") {
            li.className = "File";
            li.setAttribute("PathToFile", objTree[key]);
            li.innerHTML = key;

            /*let a = document.createElement("a");
            a.setAttribute("href","#");
            li.appendChild(a);
            a.innerHTML = key;*/
            /*li.addEventListener("click",() => {
                pathClick = objTree[key];
                console.log(pathClick);
            });*/
            li.onclick = function(){
                alert(this.innerHTML);
            };

            /*console.log(li);
            li.onclick = function(){alert("asd")};
            console.log(li);*/

        } else {
            li.className = "Folder";
        }
        let childrenUl = createTreeDom(objTree[key]);

        if (childrenUl) li.appendChild(childrenUl);

        ul.appendChild(li);
    }

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