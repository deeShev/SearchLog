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
            document.getElementById("next_button").disabled = true;
        } else {
            resp.json().then(contentPage => {
                if (document.getElementById("back_button").disabled) {
                    document.getElementById("back_button").disabled = false;
                }
                let span = document.getElementById("text_content_span");
                span.innerText = contentPage;
            })
        }
    }).catch(err => {
        console.log(err);
    })
};

