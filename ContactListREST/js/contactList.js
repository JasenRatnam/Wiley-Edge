//File for use in Ajax lesson
$(document).ready(function(){
    loadContacts();
    addContact();
    updateContact();
});

//requires no arguments.
function loadContacts() {

    //clear tables
    clearContactTable();

    //tell the function which HTML element it will use
    var contentRows = $('#contentRows');

    //does the work of retrieving data from the web service.
    $.ajax({
        type: 'GET',
        url: 'http://contactlist.us-east-1.elasticbeanstalk.com/contacts',
        success: function(contactArray) {

            //will retrieve records from contactArray as its parameter.
            
            //.each function to run a for each loop through the contacts dataset.
            $.each(contactArray, function(index, contact){

                //Create a name variable that includes firstName and lastName 
                //Create a company variable that includes the company name

                var name = contact.firstName + ' ' + contact.lastName;
                var company = contact.company;
                var contactId = contact.contactId;

                //builds a row of the table in HTML.
                
                var row = '<tr>';
                    
                    //concatenate the individual cells (&lt;td&gt;) to the row
                    row += '<td>' + name + '</td>';
                    row += '<td>' + company + '</td>';

                    //Buttons for Edit and Delete, using Bootstrap to format the buttons.
                    row += '<td><button type="button" class="btn btn-info" onclick="showEditForm('+contactId+')">Edit</button></td>';
                    row += '<td><button type="button" class="btn btn-danger" onclick="deleteContact(' + contactId + ')">Delete</button></td>';
                    //terminate the row 
                    row += '</tr>';
                
                //append row to row in table
                contentRows.append(row);
            })

        },
        error: function() {

            //generate error messages that will display in the 
            //#errorMessages list we created in the HTML file.
            $('#errorMessages')
            //appends a list item element to the class.
            .append($('<li>')
            //assigns two class attributes to the list element
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service. Please try again later.'));
        }

    })
}

//The function is triggered by clicking the #addContact button. 
function addContact() {
    $('#addButton').click(function (event) {
        var haveValidationErrors = checkAndDisplayValidationErrors($('#addForm').find('input'));
        
        if(haveValidationErrors) {
            return false;
        }

        //triggers an Ajax call that uses POST 
        //to add the new data to the contact endpoint in our web service.
        $.ajax({
           type: 'POST',
           url: 'http://contactlist.us-east-1.elasticbeanstalk.com/contact',
           //format the data as a JSON string.
           data: JSON.stringify({
                //pulls the data from the form fields
                firstName: $('#addFirstName').val(),
                lastName: $('#addLastName').val(),
                company: $('#addCompany').val(),
                phone: $('#addPhone').val(),
                email: $('#addEmail').val()
           }),
           //add the Accept and Content-Type headers 
           //to tell the service that the data is formatted in JSON,
           headers: {
               'Accept': 'application/json',
               'Content-Type': 'application/json'
           },
           'dataType': 'json',
           
           //If the POST is successful, we then reset the #errorMessages element to be empty, 
           //clear the values from the fields,
           //run the loadContacts function to refresh the table in the page.
           success: function() {
               $('#errorMessages').empty();
               $('#addFirstName').val('');
               $('#addLastName').val('');
               $('#addCompany').val('');
               $('#addphone').val('');
               $('#addEmail').val('');
               loadContacts();
           },
           //reuse the same error settings we used in the loadContacts function.
           error: function () {
               $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.')); 
           }
        })
    });
}

// function that will clear the table before loadContacts adds the new records.
// clears out the #contentRows tbody element,
// using the jQuery .empty function to remove any existing rows in the table.
function clearContactTable() {
    $('#contentRows').empty();
}


// display this form when the user clicks the Edit button.
// when the form opens, we want to hide the table.
function showEditForm() {
    //Clear any error messages from #errorMessages
    $('#errorMessages').empty();
    
    //Hide the table by hiding #contactTableDiv
    $('#contactTableDiv').hide();

    //Show #editFormDiv, making the Edit Contact form visible
    $('#editFormDiv').show();
}

//activate the Cancel button to re-hide the form and show the table.
//define the hideEditForm from HTMLModElement.html
function hideEditForm() {
    //Clear out any error messages
    $('#errorMessages').empty();
    
    //Clear all fields in the Edit Contact form
    $('#editFirstName').val('');
    $('#editLastName').val('');
    $('#editCompany').val('');
    $('#editPhone').val('');
    $('#editEmail').val('');

    //Show the table
    //Hide the Edit Contact form
    $('#contactTableDiv').show();
    $('#editFormDiv').hide();
}

//tell our program which record to display in the form
//include contactId as the parameter for the function
function showEditForm(contactId) {

    //clear out any error messages that may currently be displayed.
    $('#errorMessages').empty();
    
    //use an Ajax call using the contact ID value to retrieve the requested record.
    $.ajax({
        type: 'GET',
        //define the endpoint using a URL that references a single contact, 
        //and we append the contactId value to that.
        url: 'http://contactlist.us-east-1.elasticbeanstalk.com/contact/' + contactId,
        success: function(data, status) {

            //retrieve each value associated with the ID
            $('#editFirstName').val(data.firstName);
            $('#editLastName').val(data.lastName);
            $('#editCompany').val(data.company);
            $('#editPhone').val(data.phone);
            $('#editEmail').val(data.email);
            $('#editContactId').val(data.contactId);
            
        },
        error: function() {
            //generic error message that will appear if the application can't retrieve the data.
            $('#errorMessages')
            .append($('<li>')
            .attr({class: 'list-group-item list-group-item-danger'})
            .text('Error calling web service. Please try again later.')); 
        }
    })
    
    $('#contactTableDiv').hide();
    $('#editFormDiv').show();
}

//map the updates to a specific existing contactId using PUT
//update a contact
function updateContact(contactId) {
    $('#updateButton').click(function(event) {

        var haveValidationErrors = checkAndDisplayValidationErrors($('#editForm').find('input'));

        if(haveValidationErrors) {
            return false;
        }

        $.ajax({
            type: 'PUT',
            //point to a specific contact by including contactId as a parameter of the function.
            url: 'http://contactlist.us-east-1.elasticbeanstalk.com/contact/' + $('#editContactId').val(),
            
            //format the data before sending it
            data: JSON.stringify({
                contactId: $('#editContactId').val(),
                firstName: $('#editFirstName').val(),
                lastName: $('#editLastName').val(),
                company: $('#editCompany').val(),
                phone: $('#editPhone').val(),
                email: $('#editEmail').val()
            }),

            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },

            //Specify that the data is in JSON
            'dataType': 'json',

            'success': function() {
                //Clear any existing error messages
                $('#errorMessage').empty();
                //Hide the Edit Contact form
                hideEditForm();
                //Reload the contacts table using the loadContacts function
                loadContacts();
            },

            'error': function() {
                //same error message
                $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.')); 
            }
        })
    })
}

//wire up the Delete button to a JavaScript function 
//allow us to delete a record from the dataset.
function deleteContact(contactId) {
    //use an Ajax DELETE call to delete the record.
    $.ajax({
        type: 'DELETE',
        url: 'http://contactlist.us-east-1.elasticbeanstalk.com/contact/' + contactId,
        success: function() {
            //close the form and reload the table.
            loadContacts();
        }
    });
}

//returns a value named input.
function checkAndDisplayValidationErrors(input) {
    //clears out the #errorMessages div
    $('#errorMessages').empty();
    
    //create a new errorMessages array variable
    //store messages we receive from the form when the user clicks a submit button.
    var errorMessages = [];
    
    //jQuery each loop
    //Check for errors
    //concatenate the field label with the corresponding error message 
    input.each(function() {
        if (!this.validity.valid) {
            var errorField = $('label[for=' + this.id + ']').text();
            errorMessages.push(errorField + ' ' + this.validationMessage);
        }  
    });
    
    //Check to see if there are any items in the errorMessages array.
    if (errorMessages.length > 0){
        //uses jQuery each to append each message from the array to the #errorMessages div,
        $.each(errorMessages,function(index,message) {
            $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text(message));
        });
        // return true, indicating that there were errors
        return true;
    } else {
        // return false, indicating that there were no errors
        return false;
    }
}