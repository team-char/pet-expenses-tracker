'use strict';
/* eslint-disable */

var expenseContainer = document.getElementById('expense-list');
var expenseList = expenseContainer.children;

function loadEditDelete() {

    for (var i = 0; i < expenseList.length; i++) {
        var current = expenseList[i];
        console.log('current', current);

        // on hover, show hide button-container
        current.addEventListener('mouseover', handleExpenseHoverStart);
        current.addEventListener('mouseleave', handleExpenseHoverEnd);

        // click edit, hide delete button, show submit button
        var editButton = current.children[1].children[0];
        editButton.addEventListener('click', handleEditClick);

        var submitButton = current.children[1].children[2];
        submitButton.addEventListener('click', handleSubmitClick);

        // click delete, hide edit button, show confirm button
        var deleteButton = current.children[1].children[1];
        deleteButton.addEventListener('click', handleDeleteClick);
    }
}

// event handlers
function handleExpenseHoverStart() {

}

function handleExpenseHoverEnd() {

}

function handleEditClick() {
    var current = this.parentElement.parentElement;
    var buttonContainer = this.parentElement;
    var deleteButton = buttonContainer.children[2];
    var card = current.children[0];
    var children = card.children;

    // make fields editable
    for (var i = 0; i < children.length; i++) {
        children[i].children[0].setAttribute('contentEditable', 'true');
    }

    // hide delete button
    deleteButton.style.display = 'none';
}

function handleSubmitClick() {

    fetch('/edit-expense', {
        method: 'PUT',
        credentials: 'include',
        body: ''
    })
        .then(function(response) {
            console.log('response.json()', response.json());
        })
}

function handleDeleteClick() {
    var current = this.parentElement.parentElement;
    var buttonContainer = this.parentElement;
    var card = current.children[0];
    var id = card.dataset.id;

    fetch(`/expenses/${id}`, {
        method: 'POST',
        credentials: 'include'
        })
        .then(function(response) {
            console.log(response);
        })
        .catch(function(error) {
            console.log('error', error);
        });
}

loadEditDelete();
