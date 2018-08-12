/**
 * Selecting the text of a file page
 */
const highlightText = () => {
    let span = document.getElementById("text_content_span");
    let rng, sel;
    rng = document.createRange();
    rng.selectNode(span);
    sel = window.getSelection();
    sel.removeAllRanges();
    sel.addRange(rng);
};