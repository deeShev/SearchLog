/**
 * Getting the next page.
 * @param pathToFile path to file
 */
const getNextContentPage = (pathToFile) => {
    let startContent = getStartContent();
    let endContent = getEndContent();

    startContent += SIZE_PAGE;
    endContent += SIZE_PAGE;

    setStartContent(startContent);
    setEndContent(endContent);


    getContent(pathToFile, startContent, endContent).then(resp => {
        if (resp.status === 404) {
            startContent -= SIZE_PAGE;
            endContent -= SIZE_PAGE;

            setStartContent(startContent);
            setEndContent(endContent);
            establishVisibility(document.getElementById("next_button"), "none");
        } else {
            resp.json().then(contentPage => {
                let buttonStyle = getComputedStyle(document.getElementById("back_button"));
                if (buttonStyle.pointerEvents === "none") {
                    establishVisibility(document.getElementById("back_button"), "visible");
                }
                let span = document.getElementById("text_content_span");
                span.innerText = contentPage;
            })
        }
    }).catch(err => {
        console.log("Error retrieving content: " + err);
    })
};

