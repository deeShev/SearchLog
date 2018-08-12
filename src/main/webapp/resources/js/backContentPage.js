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
                if (document.getElementById("next_button").disabled){
                    document.getElementById("next_button").disabled = false;
                }else if (startContent === 0 && endContent === SIZE_PAGE){
                    document.getElementById("back_button").disabled = true;
                }
                let span = document.getElementById("text_content_span");
                span.innerText = contentPage;
            })
        }).catch(err => console.log(err))
    }
};
