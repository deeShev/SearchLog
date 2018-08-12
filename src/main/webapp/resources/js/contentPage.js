const SIZE_PAGE = 10; //Number of items per page

let currentPath;
let startContent;
let endContent;

const setStartContent = (valueStartContent) => {
    startContent = valueStartContent;
};

const getStartContent = () => {
    return startContent;
};


const setEndContent = (valueEndContent) => {
    endContent = valueEndContent;
};

const getEndContent = () => {
    return endContent;
};


/**
 * Receiving the contents of a file
 * @param pathFile path to file
 * @param startContent start page
 * @param endContent end page
 * @return {Promise<Response>}
 */
const getContent = (pathFile, startContent, endContent) => {
    return fetch(`http://localhost:8080/content?pathFile=${pathFile}&startContent=${startContent}&endContent=${endContent}`);
};

/**
 * Get content page on the path to the file
 * @param pathToFile path to file
 */
const getContentPage = (pathToFile) => {
    currentPath = pathToFile;
    getContent(pathToFile, 0, SIZE_PAGE).then(resp => {
        resp.json().then(contentPage => {
            startContent = 0;
            endContent = SIZE_PAGE;
            addPageInHTML(contentPage);
        })
    }).catch(err => console.log(err))
};

/**
 * add page content in html page
 * @param contentPage
 */
const addPageInHTML = (contentPage) => {
    let div = document.getElementById("content");
    let p = document.getElementById("text_content");
    let span = document.getElementById("text_content_span");
    if (span != null) {
        span.innerText = contentPage;
        document.getElementById("next_button").disabled = false;
        document.getElementById("back_button").disabled = true;
    } else {
        p = document.createElement("p");
        p.id = "text_content";
        span = document.createElement("span");
        span.id = "text_content_span";
        span.innerText = contentPage;
        p.appendChild(span);
        div.appendChild(p);
        addButton(div);
        addEventToButton();
    }
};

/**
 *  add buttons in document DOM
 * @param element
 */
const addButton = (element) => {
    let buttonBack = settingButton("back_button", "navigation_button", "Back", true);
    let buttonNext = settingButton("next_button", "navigation_button", "Next", false);
    let buttonHighlight = settingButton("highlight_button", "navigation_button", "Select all", false);
    addButtonInRootElement(element, [buttonBack, buttonNext, buttonHighlight]);

};

/**
 * add button in document DOM
 * @param element in document
 * @param arrayButtons array buttons
 */
const addButtonInRootElement = (element, arrayButtons) => {
    if (Array.isArray(arrayButtons)) {
        arrayButtons.forEach((button) => {
            element.appendChild(button);
        });
    } else {
        throw new Error('An incorrect parameter is passed ' + arrayButtons + " , expected array of buttons!");
    }
};

/**
 * Setting buttons
 */
const settingButton = (idButton, classNameButton, value, disabled) => {
    let button = document.createElement("button");
    button.id = idButton;
    button.className = classNameButton;
    button.innerText = value;
    button.disabled = disabled;
    return button;
};

/**
 * Adding an Event to a File
 */
const addEventToButton = () => {
    let listFile = document.getElementsByClassName("navigation_button");
    [].forEach.call(listFile, (button) => {
        button.addEventListener('click', () => {
            if (button.id === "next_button") {
                getNextContentPage(currentPath);
            } else if (button.id === "back_button") {
                getBackContentPage(currentPath);
            } else if (button.id === "highlight_button") {
                highlightText();
            }
        })

    });
};