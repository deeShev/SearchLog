const SIZE_PAGE = 500; //Number of items per page

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
    pathFile = encodeURI(pathFile); // compatibility with windows path
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
        }).catch(err => console.log("Error retrieving content: " + err));
    }).catch(err => console.log("Error retrieving content: " + err));
};

/**
 * add page content in html page
 * @param contentPage
 */
const addPageInHTML = (contentPage) => {
    let div = document.getElementById("content");
    let divContentPage = document.getElementById("content_page");
    let span = document.getElementById("text_content_span");
    if (span != null) {
        span.innerText = contentPage;
        establishVisibility(document.getElementById("next_button"), "visible");
        establishVisibility(document.getElementById("back_button"), "none");
    } else {
        let divResultWindow = document.getElementsByClassName("result-window");
        divContentPage = document.createElement("div");
        divContentPage.id = "content_page";
        divResultWindow[0].insertBefore(divContentPage, div);

        span = document.createElement("span");
        span.id = "text_content_span";
        span.innerText = contentPage;

        divContentPage.appendChild(span);
        addButton(div);
        addEventToButton();
    }
};

/**
 *  add buttons in document DOM
 * @param element
 */
const addButton = (element) => {
    let buttonBack = settingButton("back_button", "navigation_button", "Back", "none");
    let buttonNext = settingButton("next_button", "navigation_button", "Next", "visible");
    let buttonHighlight = settingButton("highlight_button", "navigation_button", "Select all", "visible");
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
    let button = document.createElement("div");
    button.id = idButton;
    button.className = classNameButton;
    button.innerText = value;
    establishVisibility(button, disabled);

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

/**
 * Setting the Button Visibility
 * @param element - document element DOM
 * @param value - the visibility value in the program is used : "none" or "visible"
 */
const establishVisibility = (element, value) => {
    let button = element;
    if (value === "none") {
        button.style.pointerEvents = "none";
        button.style.opacity = "0.4";
    } else {
        button.style.pointerEvents = "visible";
        button.style.opacity = "1.0";
    }
};