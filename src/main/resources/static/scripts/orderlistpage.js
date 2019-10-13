document.addEventListener("DOMContentLoaded", function(){
    var info = document.getElementById("orderinfo");
    switch(info.innerHTML){
        case 'OPEN':
            info.innerHTML = "This order is open";
            break;
        case 'CLOSED':
            info.innerHTML = "This order is closed (No changes can be made)";
            break;
        case 'REALIZED':
            info.innerHTML = "This order has been realized (No changes can be made)";
            break;
        case 'CANCELLED':
            info.innerHTML = "This order has been cancelled (No changes can be made)";
            break;
    }
})

function confirmRemoval(closeForm, closeUrl){
    if (confirm("Are you sure about removing these items from order?")){
        closeForm.action = closeUrl;
        return true;
    }
    return false;
}