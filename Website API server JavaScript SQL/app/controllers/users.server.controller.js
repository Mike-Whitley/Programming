const user=require('../models/user.server.model');
const helper = require('../controllers/Helper_functions'); //this is needed to allow this file to access functions in helper_functions.js
const fs = require("fs");

exports.create = async function(req, res){

    console.log( '\nRequest to create a new user...' );
    let user_dictionary = {     //careful with var as this user_dictionary is useable outside the
        name: req.body.name,
        email: req.body.email,
        password: req.body.password,
        city: req.body.city,
        country: req.body.country
    };
    if (req.body.name == null){
        res.status(400)
            .send(`ERROR Invalid Email Address or user/password length`) //check with tutor if this line is fine
        return

    }else{

        if (helper.ValidateEmail(req.body.email) && helper.emptyness_test(req.body.password, req.body.name, req.body.email)) {
            try {
                const result = await user.insert_user(user_dictionary);
                res.status(201)
                    .json({
                        userId: result.id
                    });
                return

            } catch (err) {
                res.status(500)
                    .send(`ERROR creating user ${user_dictionary.name}: ${err}`);

            }
        }else{
            res.status(400)
                .send(`ERROR Invalid Email Address or user/password length`) //check with tutor if this line is fine
            return
        }
    }

};

exports.login = async function(req, res) {
    //authorize the user
    let login_details = {     //careful with var as this user_dictionary is useable outside the
        email: req.body.email,
        password: req.body.password
    };

    //create an authorization token using https://www.npmjs.com/package/rand-token rand-token most popular generated token on npm
    // Generate a 16 character alpha-numeric token:

    const found_user_id =  await user.get_user_id_from_email(login_details.email);
    try{
        const result = await user.update_token(login_details);
        if (found_user_id == null){
            res.status(400)
                .send(`Email does not exist`) //check with tutor if this line is fine
            return
        }else{
            res.status(200)
                .json({
                    userId: found_user_id,
                    token: result
                });
            return
        }

    }catch (err) {
        res.status(400)
            .send(`Internal Server Error`) //check with tutor if this line is fine
        return

    }

};

exports.logout = async function(req, res){
    const x_header = req.header('X-Authorization') || "";
    const result = await user.logout_token(x_header);
    if (result[0].changedRows >= 1) {
        res.status(200).send()
        return
    }else{
        res.status(401).send()
        return
    }
};

exports.info = async  function(req, res){
    const id = req.params.id;
    // if token matches their token then return the email else only return name city country
    const x_header = req.header('X-Authorization') || "";
    //console.log('header at the start point is',x_header)
    //console.log('id at the start point is',id)
    try{
        const user_details =  await user.get_user_details(x_header,id);
        res.status(200).send(user_details)
        return

    }catch{
        res.status(404).send("no user found")
        return
    }
};

exports.user_update = async function(req, res){

    var isEmpty = function(obj) {
        return Object.keys(obj).length === 0;
    };

    // if password if wrong return 401 not valid credentials
    // if token does not match user id they want to update return 403 error

    // return 400 when no changes are provide
    const x_header = req.header('X-Authorization') || "";
    const id = req.params.id;


    if(isEmpty(req.body)){
        res.status(400).send()
        return
    }



    const logged_in = await user.check_token(x_header,id);

    if(logged_in){
        const jsonfile = req.body;
        const user_details =  await user.update_user_details(jsonfile, id);

        if(user_details === "success"){
            res.status(200).send()
            return
        }else if(user_details === "fail"){
            res.status(500).send()
            return
        }else if(user_details === "no_change"){
            res.status(400).send()
            return
        }
    }else{
        res.status(401).send() //not logged in
        return
    }

};

exports.get_photo = async function(req, res){
    try {
    const id = req.params.id;

    const filename = await user.get_photo_name(id);
    if(filename === "no_photo"){
        res.status(404).send()
        return
    }else {

        // console.log(filename);
        try {
            const photos_file_path = "./storage/photos/" + filename;
            // console.log(photos_file_path);
            res.download(photos_file_path)
        }catch(err){
            res.status(401).send()
            return

        }
    }
    }catch{
        res.status(500).send()
        return
    }

        //.sendFile(filename, {root: "/home/cosc/student/mkw60/Desktop/seng project/mkw60/storage/photos"});
};

exports.set_photo = async function(req, res) {
    const given_id = req.params.id;

    const x_header = req.header('X-Authorization') || "";

    let contentType = req.get("Content-Type");
    contentType = contentType.split("/");
    contentType = contentType[1];
    // console.log(contentType);

    const types_of_photos = ["png", "jpeg", "gif", "jpeg"];

    let returned_id = await user.get_authorization_id(x_header);
    //if user doesn't exist return 404
    const user_id_check = await user.check_user_id_exists(given_id);
    if(user_id_check == "no_user_exists"){
        res.status(404).send()
        return
    }

    if (returned_id === "error_no_auth") {
        res.status(401).send()
        return
    } else if (returned_id != given_id) {
        res.status(403).send()
        return
    }else if(!types_of_photos.includes(contentType)){
        console.log("wrong file type")
        res.status(400).send()
        return
    } else {
        let file_name = `user_${given_id}.${contentType}`;
        const update_photo = await user.get_photo_name(given_id);
        console.log(update_photo)
        if(update_photo == "no_photo"){
            console.log("const update_photo = await user.get_photo_name(given_id);got here")
            fs.writeFile(`./storage/photos/${file_name}`, req.body,  "binary",function(err) {
                if (err) {
                    console.log(err);
                    res.status(400).send()
                    return
                } else {
                    console.log("The new file was updated!");
                    const result = user.set_photo_name(given_id, file_name)
                    res.status(201).send()
                    return
                }
            });
        }else{

            fs.writeFile(`./storage/photos/${file_name}`, req.body,  "binary",function(err) {
                if (err) {
                    console.log(err);
                    res.status(400).send()
                    return
                } else {
                    console.log("The file was created!");
                    const result = user.set_photo_name(given_id, file_name)
                    res.status(200).send()
                    return
                }
            });

        }


    }
};

exports.delete_photo = async function(req, res){


    try{
        const x_header = req.header('X-Authorization') || "";
        let returned_id = await user.get_authorization_id(x_header);
        const id = req.params.id;
        const filename = await user.get_photo_name(id);
        // console.log(x_header);
        const exists = await user.check_user_id_exists(id);


        if(x_header == undefined) {
            res.status(401).send();
            return
        }
        if(exists == "no_user_exists"){
            console.log("user not found");
            res.status(404).send();
            return
        }else if(returned_id == "error_no_auth"){

            console.log("401 error Unauthorized user trying to delete someone elses image");
            res.status(401).send();
            return
        }

        if (returned_id != id) {
            console.log("403 error Forbidden user has no auth token");
            res.status(403).send();
            return
        }

        if (filename == "no_photo") {
            console.log("no_photo to delete");
            res.status(404).send();
            return
        } else{
             const setphotoname = await user.delete_photo_name(id);
            console.log("file deletd");
            res.status(200).send()
            return
                }
        } catch {
            res.status(500).send()
        return
        }
};


exports.get_petition = async function(req, res){

    const jsonfile = req.query;

    // let check_catagory_id = await user.check_catagory_id_exists(jsonfile.categoryId);
    //
    // if(check_catagory_id == "no_id_exists"){
    //     res.status(400).send()
    //     return
    // } this shit is causing the errors

    const petitions = await user.return_petitions(jsonfile);
    if(petitions == "sqlerror"){
        res.status(400).send()
        return
    }else{
        res.status(200).send(petitions)
        return
    }

};

exports.add_petition = async function(req, res){
    const x_header = req.header('X-Authorization') || "";

    if( await user.get_authorization_id(x_header) === "error_no_auth"){
        console.log('no auth ');
        res.status(401).send();
        return;

    }else {


        try {
            if (req.body.title.length == 0) {
                res.status(400).send()
                return
            }
        } catch {
            res.status(400).send()
            return
        }

        if (x_header.length < 1) {
            res.status(401).send("user not logged in")
            return
        }

        const petition_details = {
            title: req.body.title,
            description: req.body.description,
            categoryId: req.body.categoryId,
            closingDate: req.body.closingDate
        };


        const petitions = await user.create_petition(petition_details, petition_details.categoryId, x_header);
        if(petitions =="sqlerror"){
            res.status(400).send()
            return
        }
        if (petitions === "date_past") {
            res.status(400).send()
            return
        } else if (petitions === "does_not_exist") {
            res.status(400).send()
            return
        } else if (petitions === "notuser") {
            res.status(401).send()
            return

        } else if (petitions === "error_no_auth") {
            res.status(401).send()
            return
        } else {
            res.status(201).json({
                petitionId: petitions.petition_id
            });
            return
        }
    }

};

exports.get_petition_photo = async function(req, res) {
    try {
        const id = req.params.id;

        const filename = await user.get_petition_photo_name(id);
        if(filename == "no_photo"){
            console.log("no photo saved");
            res.status(404).send()
            return
        }else {

            console.log(filename);
            try {
                const photos_file_path = "./storage/photos/" + filename;
                console.log(photos_file_path);
                res.download(photos_file_path)
            }catch(err){
                console.log("error file not found");
                res.status(404).send()
                return

            }
        }
    }catch{
        res.status(500).send()
        return
    }
};

exports.update_petition_photo = async function(req, res) {
    const unique_petition_id = req.params.id;
    const x_header = req.header('X-Authorization') || "";
    let returned_id = await user.get_authorization_id(x_header);
    // console.log("header = ",x_header)
    // console.log("returned id for that header is ", returned_id)


    if (returned_id === "error_no_auth") { //CHECK USER HAS A AUTHORIZATION TOKEN FIRST
        res.status(401).send();
        return
    }

    if (!await user.petition_existance_check(unique_petition_id)) {
        console.log("petition doesn't exist");
        res.status(404).send();
        return
    }



    let contentType = req.get("Content-Type");
    contentType = contentType.split("/");
    contentType = contentType[1];

    const types_of_photos = ["png", "jpeg", "gif", "jpg"];
    //
    // console.log("passing in ", unique_petition_id)
    let petiton_user_id = await user.get_petiton_user_id(unique_petition_id);
    // console.log("passing out", unique_petition_id)
    // console.log("user_id = ",petiton_user_id)
    //

    if (!types_of_photos.includes(contentType)) {
        console.log("wrong file type");
        res.status(400).send();
        return
    }
    if (returned_id != petiton_user_id) {
        console.log("returned_id and petit id", returned_id, petiton_user_id); //user id = 11
        res.status(403).send();
        return
    }

        let file_name = `petition_${unique_petition_id}.${contentType}`;
        const update_photo = await user.get_petition_photo_name(unique_petition_id);
        // console.log(update_photo)
        if(update_photo == null){
            console.log("const update_photo = await user.get_photo_name(given_id)got here")
            fs.writeFile(`./storage/photos/${file_name}`, req.body,  "binary",function(err) {
                if (err) {
                    console.log(err);
                    res.status(400).send()
                    return
                } else {
                    console.log("The new file was updated!");
                    const result =  user.set_petition_photo_name(unique_petition_id, file_name)
                    res.status(201).send()
                    return
                }
            });
        }else{

            fs.writeFile(`./storage/photos/${file_name}`, req.body,  "binary",function(err) {
                if (err) {
                    console.log(err);
                    res.status(400).send()
                    return
                } else {
                    console.log("The file was created!");
                    const result =  user.set_petition_photo_name(unique_petition_id, file_name)
                    res.status(200).send()
                    return
                }
            });

        }
};

exports.retrieve_signatures = async function(req, res){
    const petition_id = req.params.id;

    const result = await user.signature_sql_id_query(petition_id);
    const str = JSON.stringify(result);
    const intermitent = str.replace(/signatory_id/g, 'signatoryId');
    const intermitent2 = intermitent.replace(/signed_date/g, 'signedDate');
    const answer = JSON.parse(intermitent2);
    if(result.length < 1 | result == "fail"){
        res.status(404).send()
        return
    }else{
        res.status(200).send(answer)
        return
    }


};
exports.sign_petition = async function(req, res) {
        const petition_id = req.params.id;
        const auth = req.header('X-Authorization') || "";

        const date_check = await user.date_passed(petition_id); //good

        let user_id = await user.get_authorization_id(auth); //good

        let already_signed = await user.already_signed_petition(user_id,petition_id);

        const check = await user.signature_sql_id_query(petition_id);

        if (user_id === "error_no_auth") { //CHECK USER HAS A AUTHORIZATION TOKEN FIRST
            res.status(401).send();
            return
        }
        if(check.length < 1 | check == "fail"){
        res.status(404).send()
            return
        }

        if(date_check === "datepassed"){
            console.log("date_check has been tripped");
            res.status(403).send();
            return
        }

        if(already_signed === true) {
            console.log("already_signed has been tripped");
            res.status(403).send();
            return
        }else{

        await user.sign_petition_query(user_id,petition_id);
        res.status(201).send()

        }

    };

exports.delete_signature = async function(req, res) {
    const petition_id = req.params.id;
    const auth = req.header('X-Authorization') || "";
    let user_id = await user.get_authorization_id(auth); //good

    ///A user cannot remove a signature from a petition they haven't signed, from a petition they created, or from a petition that has closed.
    //return 403 when a user tries to delete their signature when it doesn't exist
    const answer = await user.already_signed_petition(user_id,petition_id);
    const petition_exists = await user.petition_existance_check(petition_id); //done

    if (user_id === "error_no_auth") { //CHECK USER HAS A AUTHORIZATION TOKEN FIRST
        res.status(401).send();
        return
    }

    if(petition_exists == false){
        res.status(404).send()
        return
    }


    if(answer == false){
        res.status(403).send()
        return
    }else{
        await user.remove_signature_from_db(user_id,petition_id);
        res.status(200).send()
    }

};

exports.get_petition_catagories = async function(req, res) {

    const catagories = await user.get_petition_catagories()
    // console.log(catagories)
    res.status(200).send(catagories)
    return


};

exports.delete_petition = async function(req, res) {
    const petition_id = req.params.id;
    const auth = req.header('X-Authorization') || "";

    const author_id = await user.get_petiton_user_id(petition_id);
    let user_id = await user.get_authorization_id(auth);
    console.log("author_id = ",author_id)
    console.log("user_id = ",user_id)


    if(!await user.petition_existance_check(petition_id)){
        console.log("404 petition does not exist");
        res.status(404).send()
        return
    }

    if (user_id === "error_no_auth") { //CHECK USER HAS A AUTHORIZATION TOKEN FIRST
        res.status(401).send();
        return
    }

    if(author_id != user_id){
        console.log("incorrect author id");
        res.status(403).send()
        return
    }


    const removal = await user.delete_petition_from_sql(petition_id);
    if(removal === "deleted"){
        res.status(200).send()
        return
    }



};


exports.update_petition_details = async function(req, res) {
    console.log("json body sent through = ", req.body)
    const petition_id = req.params.id;
    const auth = req.header('X-Authorization') || "";
    let user_id = await user.get_authorization_id(auth);
    const author_id = await user.get_petiton_user_id(petition_id);


    var isEmpty = function(obj) {
        return Object.keys(obj).length === 0;
    };

    if(isEmpty(req.body)){
        console.log("the body is empty this is the 400 error")
        res.status(400).send()
        return
    }

    if (user_id === "error_no_auth") { //CHECK USER HAS A AUTHORIZATION TOKEN FIRST
        res.status(401).send();
        return
    }

    if(author_id != user_id){
        console.log("incorrect author id");
        res.status(403).send();
        return
    }

    const petition_exists = await user.petition_existance_check(petition_id)
    console.log("petition_exists",petition_exists)
    if(!petition_exists){
        console.log("404 petition does not exist");
        res.status(404).send();
        return
    }


    if (req.body.hasOwnProperty("closingDate")) {
        const close_date = req.body.closingDate;
        const date_check = await user.date_check(close_date); //good
        console.log("date_check",date_check);

        if (date_check === "datepassed") {
            console.log("datepassed has been tripped this is the 400 error")
            res.status(400).send();
            return
        }

    }




    const update = await user.update_petition_from_sql(petition_id,req.body);

    if(update === "success"){
        res.status(200).send()
        return
    }else if(update === "no_change") {
        console.log("this is the change being made :O")
        res.status(400).send()
        return
    }else{
        res.status(500).send()
        return
    }

};

exports.get_petition_details = async function(req, res) {
    const petition_id = req.params.id;

    const petition_exists = await user.petition_existance_check(petition_id)
    // console.log("petition_exists",petition_exists)
    if(!petition_exists){
        console.log("404 petition does not exist");
        res.status(404).send();
        return
    }else{
        const jsonfile = await user.sql_petition_get(petition_id);
        res.status(200).send(jsonfile);
        return
    }



};
