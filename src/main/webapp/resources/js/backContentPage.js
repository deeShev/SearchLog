/**
 * Getting the back page.
 * @param pathToFile path to file
 */
const getBackContentPage = (pathToFile) => {
    let startContent = getStartContent();
    let endContent = getEndContent();

    if (startContent !== 0) {
        startContent -= SIZE_PAGE;
        endContent -= SIZE_PAGE;

        setStartContent(startContent);
        setEndContent(endContent);

        getContent(pathToFile, startContent, endContent).then(resp => {
            resp.json().then(contentPage => {
                let buttonStyle = getComputedStyle(document.getElementById("next_button"));
                if (buttonStyle.pointerEvents === "none") {
                    establishVisibility(document.getElementById("next_button"), "visible");
                }
                if (startContent === 0 && endContent === SIZE_PAGE) {
                    establishVisibility(document.getElementById("back_button"), "none");
                }
                let span = document.getElementById("text_content_span");
                span.innerText = contentPage;
            })
        }).catch(err => console.log("Error retrieving content: " + err))
    }
};
