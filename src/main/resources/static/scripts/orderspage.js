document.addEventListener("DOMContentLoaded", function(){
    if(window.location.href === "http://localhost:8080/orders") {
        var table = document.getElementById("order");
        var tbody = table.getElementsByTagName("tbody");
        var tr = tbody[0].getElementsByTagName("tr");
        var buttons = tbody[0].getElementsByClassName("btn btn-danger");
        for (var i = 0; i < tr.length; i++) {
            if (tr[i].getElementsByTagName("td")[1].innerHTML === "OPEN") {
                buttons[i].style.display = "block";
            }
        }
    } else if(window.location.href === "http://localhost:8080/manageorders"){
        var tablead = document.getElementById("order");
        var tbodyad = tablead.getElementsByTagName("tbody");
        var trad = tbodyad[0].getElementsByTagName("tr");
        var changeform = tbodyad[0].getElementsByClassName("statusform");
        for (var i = 0; i < trad.length; i++) {
            if (trad[i].getElementsByTagName("td")[1].innerHTML === "CLOSED") {
                changeform[i].style.display = "inline-block";
            }
        }
    }
})

function confirmClosing(closeForm, closeUrl){
    if (confirm("You can't make any changes to order after closing it. Are you sure?")){
        closeForm.action = closeUrl;
        return true;
    }
    return false;
}