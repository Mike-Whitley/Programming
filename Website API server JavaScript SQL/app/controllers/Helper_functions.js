const bcrypt = require('bcrypt');

exports.ValidateEmail = function (email)
//checks the email address is in format of string + @ + string
{
    const mail_format = /\S+@\S+/; //string + @ + string
    if(email.match(mail_format))
    {
        return true;
    }
    else
    {
        console.log("Your have entered an invalid email address! it does not contain the @ symbol");
        return false;
    }
};

exports.emptyness_test = function (name, password, email){
    //checks to see if the password is blank
    // console.log("dsgdsgdsgname is sfsagdagdsgdsgfdsgdsgdsgdsgdsgdsgerwg" + name)
    if (name == undefined){
        // console.log("shits undefined")
        return false;
    }else{

        if (name.length == 0 | password.length == 0 | email.length == 0){
            return false;
        }else{
            return true;
        }
    }
};


exports.hash_password= async function (password) {
    //retrieved from https://www.npmjs.com/package/bcrypt
    const saltRounds = 10;
    return await bcrypt.hash(password, saltRounds);
};

//token when they log in they must be given a token .. add this token to the DB - chang every time they log in


