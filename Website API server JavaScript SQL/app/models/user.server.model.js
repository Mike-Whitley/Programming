const db =require('../../config/db');
const helper = require('../controllers/Helper_functions');//this is needed to allow this file to access functions in helper_functions.js
var randtoken = require('rand-token');// this is required for the login token generation https://www.npmjs.com/package/rand-token
var moment = require('moment');


exports.insert_user = async function(details){

    console.log(`Request to insert ${details.name} into the database....`);

    const items = [details.name, details.email, await helper.hash_password(details.password), details.city, details.country];
    const conn = await db.getPool().getConnection();
    const query = 'INSERT into User (name, email, password, city, country) VALUES (?, ?, ?, ?, ?) ';

    const [ result, _ ] = await conn.query(query, items);
    conn.release();
    return {id : result.insertId};

};


exports.update_token = async function(details){

    console.log(`Request to insert token into the database....`);
    var token = randtoken.generate(32);
    //console.log(token)
    try{
 // assumption 32 characters over 16 as sql has slot for 32 varchar
        const query = 'UPDATE User SET auth_token = ? WHERE email = ?';
        const items = [token, details.email];
        const conn = await db.getPool().getConnection();
        await conn.query(query, items);
        conn.release();
        return token

    }catch(err){
        return null;
    }

};

exports.get_user_id_from_email = async function(email) {

    const query =  'SELECT user_id FROM User WHERE email = ?';
    try {
        const conn = await db.getPool().getConnection();
        const user_id = await conn.query(query, email);
        conn.release();
        return (user_id[0][0].user_id)
    } catch (err) {
        return null;
    }
};

exports.logout_token = async function(x_header){

    const query =  'UPDATE User SET auth_token = NULL WHERE auth_token = ?';
    try {
        const conn = await db.getPool().getConnection();
        const result = await conn.query(query, x_header);
        conn.release();
        return result

    } catch (err) {
        return null;
    }

};

exports.check_token = async function(x_header,id){
    const query =  'SELECT auth_token FROM User WHERE user_id = ?';
    //console.log("HEADER AND ID ====",x_header,id);
    const conn = await db.getPool().getConnection();
    //console.log("id is", x_header)
    const rows = await conn.query(query, id);
    conn.release();
    return (rows[0][0].auth_token == x_header)
};

exports.get_user_details= async function(x_header,id){
    //console.log("the id here is",id, x_header)
    const  logged_in = await exports.check_token(x_header,id);
    const query =  'SELECT * FROM User WHERE user_id = ?';
    const conn = await db.getPool().getConnection();
    const rows = await conn.query(query, id);
    const element = rows[0][0];
    conn.release();
    if(logged_in){
        return{
            "name": element.name,
            "city": element.city,
            "country": element.country,
            "email": element.email
        }
    }else
        return{
            "name": element.name,
            "city": element.city,
            "country": element.country
        }

};

exports.check_user_id_exists = async function(id) {
    try {
        const query = 'SELECT * FROM User WHERE user_id = ?';
        const conn = await db.getPool().getConnection();
        const rows = await conn.query(query, id);
        const exists = rows[0]
        conn.release();
        if(exists.length < 1){
            return("no_user_exists")
        }else{
            return rows[0]
        }
        console.log("result",exists)
    }catch(err){
        conn.release();
        console.log("error",err)
    }

};

exports.petition_existance_check = async function(id) {
    try {
        const query = 'SELECT * FROM Petition WHERE petition_id = ?';
        const conn = await db.getPool().getConnection();
        const rows = await conn.query(query, id);
        const exists = rows[0]
        console.log("result =",exists.length)
        conn.release();
        if(exists.length < 1){
            return(false)
        }else{
            return true
        }
        console.log("result",exists)
    }catch(err){
        console.log("error",err)
    }

};




exports.update_user_details = async function(jsonfile, id) {

    try {
        let starter_query = ("UPDATE User SET ")
        let item = [];
        if (jsonfile.hasOwnProperty("name")) {
            console.log("added name");
            const a = "name = ?, ";
            starter_query = starter_query.concat(a);
            item.push(jsonfile.name)

        }

        if (jsonfile.hasOwnProperty("email")) {
            console.log("added email");
            const b = "email = ?, ";
            starter_query = starter_query.concat(b);
            item.push(jsonfile.email)
        }

        if (jsonfile.hasOwnProperty("password")) {
            console.log("added password");
            const c = "password = ?, ";
            starter_query = starter_query.concat(c);
            item.push(jsonfile.password)
        }

        if (jsonfile.hasOwnProperty("city")) {
            console.log("added city");
            const d = "city = ?, ";
            starter_query = starter_query.concat(d);
            item.push(jsonfile.city)

        }

        if (jsonfile.hasOwnProperty("country")) {
            console.log("added country");
            const e = "country = ?, ";
            starter_query = starter_query.concat(e);
            item.push(jsonfile.country)
        }

        item.push(id);
        var str = starter_query.substring(0, starter_query.length - 2);
        const b = " WHERE user_id = ?";
        starter_query = str.concat(b);
        const conn = await db.getPool().getConnection();
        const rows = await conn.query(starter_query, item);
        // console.log('row change = ', rows[0].changedRows);
        // console.log('the type of value this is',typeof(rows[0].changedRows));
        conn.release();
        if(rows[0].changedRows > 0){
            console.log("this is a sucess");
            return("success")
        }else{
            console.log("this is a fail");
            return("no_change")
        }
    }catch{
        return("fail")
    }

};

exports.get_photo_name = async function(id) {
    try {
        const query = 'SELECT photo_filename FROM User WHERE user_id = ?';
        //console.log("HEADER AND ID ====",x_header,id);
        const conn = await db.getPool().getConnection();
        //console.log("id is", x_header)
        const rows = await conn.query(query, id);
        conn.release();
        console.log("rows[0][0].photo_filename",rows[0][0].photo_filename)
        if(rows[0][0].photo_filename == null){
            return("no_photo")
        }else{
            return (rows[0][0].photo_filename)
        }
    }catch(err){
        return("sql error")
    }

};



exports.get_petition_photo_name = async function(id) {
    try {
        const query = 'SELECT photo_filename FROM Petition WHERE petition_id = ?';
        //console.log("HEADER AND ID ====",x_header,id);
        const conn = await db.getPool().getConnection();
        //console.log("id is", x_header)
        const rows = await conn.query(query, id);
        conn.release()
        if(rows[0][0].photo_filename == null){
            return("no_photo")
        }else{
            return (rows[0][0].photo_filename)
        }
    }catch(err){
        return("sql error")
    }

};


exports.set_photo_name = async function(id, filename) {
    const query =  'UPDATE User SET photo_filename=? WHERE user_id = ?';
    try {
        const conn = await db.getPool().getConnection();
        const values = [filename, id];
        const rows = await conn.query(query, values);
        conn.release();
        console.log("photo update compete");
        return("Success")
    }catch{
        console.log("fail")
    }

};

exports.delete_photo_name = async function(id) {
    const query =  'UPDATE User SET photo_filename=NULL WHERE user_id = ?';
    try {
        const conn = await db.getPool().getConnection();
        const rows = await conn.query(query, id);
        conn.release();
        console.log("photo delete compete");
        return("Success")
    }catch{
        console.log("fail")
    }

};


exports.create_petition = async function(petition_details,category_id,x_header){
    const petition_date = petition_details.closingDate;
    const current_date = Date.now(); // this is todays date
    const petition_date_in_ms = new Date(petition_date); // date object
    const date = petition_date_in_ms.getTime(); //date to ms
    if(current_date > date){
        return("date_past")
    }else{
        const id = await exports.get_authorization_id(x_header)
        if(id == "error_no_auth"){
            return("error_no_auth")
        }
        const query =  'SELECT category_id FROM Petition WHERE category_id = ?';
        //console.log("HEADER AND ID ====",x_header,id);
        const conn = await db.getPool().getConnection();
        //console.log("id is", x_header)
        const rows = await conn.query(query, category_id);
        conn.release();
        if(rows[0].length > 0){
            console.log("exists")
            const result = await exports.insert_petition(petition_details,category_id,id);
            //console.log("result", result)
            return result
        }else{

            console.log("does_not_exist")
            return("does_not_exist")

        }

    }

};

exports.get_authorization_id = async function(auth_token) {
    const query = 'SELECT user_id FROM User WHERE auth_token = ?;';
    try {
        const conn = await db.getPool().getConnection();
        const rows = await conn.query(query, auth_token);
        conn.release();
        return (rows[0][0].user_id)
    }catch{
        console.log("no matching auth")
        return("error_no_auth")
    }

};

exports.insert_petition = async function(petition_details,category_id,id){
    try {
        const today_date = new Date().toISOString().replace(/T/, ' ').replace(/\..+/, '');
        const query = 'INSERT INTO Petition (author_id, title, description, category_id, closing_date,created_date) VALUES (?,?,?,?,?,?)';
        const values = [id, petition_details.title, petition_details.description, petition_details.categoryId, petition_details.closingDate, today_date];
        const conn = await db.getPool().getConnection();
        const rows = await conn.query(query, values);
        conn.release();
        const string = "SELECT petition_id FROM Petition WHERE title = ?";
        const abc = await db.getPool().getConnection();
        const rows2 = await abc.query(string, petition_details.title);
        abc.release();
        //console.log("row2 = ",rows2[0][0].petition_id);
        return (rows2[0][0])
    }catch(err){
        return("sqlerror")
        console.log("error has occured")
    }
};


exports.get_petiton_user_id = async function(petitionid) {
    console.log("petitionid in function = ",petitionid)


    const query =  'SELECT author_id FROM Petition WHERE petition_id = ?';
    try {
        const conn = await db.getPool().getConnection();
        const user_id = await conn.query(query, petitionid);
        conn.release();
        return (user_id[0][0].author_id)
    } catch (err) {
        return null;
    }
};

exports.set_petition_photo_name = async function(id, filename) {
    const query =  'UPDATE Petition SET photo_filename=? WHERE petition_id = ?';
    try {
        const conn = await db.getPool().getConnection();
        const values = [filename, id];
        const rows = await conn.query(query, values);
        conn.release();
        console.log("photo update compete");
        return("Success")
    }catch{
        console.log("fail")
    }

};
exports.signature_sql_id_query = async function(petition_id) {
    const query =  'SELECT signatory_id, name, city, country, signed_date FROM Signature JOIN User ON Signature.signatory_id = User.user_id WHERE petition_id = ? ORDER BY signed_date';
    try {
        const conn = await db.getPool().getConnection();
        const rows = await conn.query(query, petition_id);
        conn.release();
        // console.log("query exists", rows[0])
        // console.log("signatures retrieved");
        return(rows[0])
    }catch{
        conn.release();
        console.log("fail")
        return("fail")
    }

};

exports.date_passed = async function(petition_id) {

    const query = 'SELECT closing_date FROM Petition WHERE petition_id = ?;';
    const conn = await db.getPool().getConnection();
    const rows = await conn.query(query, petition_id);
    conn.release()

    const closing_date = rows[0][0].closing_date;
    var close_date = new Date(closing_date); // some mock date
    var milliseconds = close_date.getTime()
    const current_date = Date.now(); // this is todays date
    if(current_date > milliseconds){
        return("datepassed")
    }else{
        return("all_good")
    }

};


exports.already_signed_petition = async function(user_id,petition_id) {
    try {
        console.log(petition_id)
        const query = 'SELECT * FROM Signature WHERE petition_id = ? AND signatory_id = ?';
        const conn = await db.getPool().getConnection();
        const values = [user_id,petition_id]
        const rows = await conn.query(query, values);
        const exists = rows[0];
        console.log("exists", exists.length)
        conn.release();
        if(exists.length >= 1){
            console.log("petition exists");
            return(true)
        }else{
            return(false)
        }
        console.log("result",exists)
    }catch(err){
        console.log("error",err)
    }

};


exports.sign_petition_query = async function(user_id,petition_id) {
    const query = 'INSERT into Signature (signatory_id, petition_id, signed_date) VALUES (?, ?, ?) ';
    const todays_date = new Date();
        // .toISOString().replace(/T/, ' ').replace(/\..+/, '');
    console.log("todays_date-------------",todays_date);
    const conn = await db.getPool().getConnection();
    const values = [user_id,petition_id,todays_date]
    const rows = await conn.query(query, values);
    conn.release();
    console.log("inserted into database")
};



exports.remove_signature_from_db = async function(user_id,petition_id) {

    const query = 'DELETE FROM Signature WHERE signatory_id = ? AND petition_id = ?';
    const values = [user_id,petition_id]
    const conn = await db.getPool().getConnection();
    const rows = await conn.query(query, values);
    conn.release();
    console.log("delete complete")

};


exports.already_signed = async function(user_id,petition_id) {

try {
    const query = 'SELECT * FROM Signature WHERE signatory_id = ? AND petition_id = ?';
    const conn = await db.getPool().getConnection();
    const values = [user_id, petition_id]
    const rows = await conn.query(query, values);
    const exists = rows[0];
    console.log("exists", exists.length)
    conn.release();
    if (exists.length >= 1) {
        console.log("already signed");
        return ("true")
    } else {
        return ("false")
    }

}catch(err){
    console.log("Error occured")
}

};

exports.get_petition_catagories = async function() {
    try {
        const query = 'SELECT DISTINCT category_id AS categoryId, name FROM Category';
        const conn = await db.getPool().getConnection();
        const rows = await conn.query(query);
        const petitions = rows[0];
        conn.release();
        // console.log(petitions)
        return petitions

    }catch(err){
        console.log("Error occured")
    }

};

exports.delete_petition_from_sql = async function(petition_id) {

    try {
        const query = 'DELETE FROM Signature WHERE petition_id = ?';
        const conn = await db.getPool().getConnection();
        const rows = await conn.query(query, petition_id);
        conn.release();

        const abc = await db.getPool().getConnection();
        const query2 = 'DELETE FROM Petition WHERE petition_id = ?';
        const rows2 = await abc.query(query2, petition_id);
        abc.release();

        console.log("delete successful")
        return("deleted")
    }catch(err){
        console.log("error",err)
    }


};


exports.update_petition_from_sql = async function(petition_id, jsonfile) {

    try {
        let starter_query = ("UPDATE Petition SET ");
        let item = [];

        if (jsonfile.hasOwnProperty("title")) {
            console.log("added title");
            const a = "title = ?, ";
            starter_query = starter_query.concat(a);
            item.push(jsonfile.title)
        }

        if (jsonfile.hasOwnProperty("description")) {
            console.log("added description");
            const b = "description = ?, ";
            starter_query = starter_query.concat(b);
            item.push(jsonfile.description)
        }
        if (jsonfile.hasOwnProperty("categoryId")) {
            console.log("added categoryId");
            const c = "category_id = ?, ";
            starter_query = starter_query.concat(c);
            item.push(jsonfile.categoryId)
        }
        if (jsonfile.hasOwnProperty("closingDate")) {
            console.log("added closingDate");
            const d = "closing_date = ?, ";
            starter_query = starter_query.concat(d);
            item.push(jsonfile.closingDate)
        }
        item.push(petition_id);
        const b = " WHERE petition_id = ?";
        var str = starter_query.substring(0, starter_query.length - 2);
        starter_query = str.concat(b);

        const conn = await db.getPool().getConnection();
        const rows = await conn.query(starter_query, item);
        conn.release();

        // console.log("query currently",starter_query)
        // console.log("items currently",item)
        console.log("the rows are as follows ===== ",rows[0])
        if (rows[0].affectedRows > 0 || rows[0].changedRows > 0) {
            console.log("this is a success");
            return ("success")
        } else {
            console.log("this is a fail on the no_change check");
            return ("no_change")
        }
    }catch(err) {
        console.log("error occured", err)
        return("fail")

    }


};

exports.date_check = async function(date_check) {
    var close_date = new Date(date_check); // some mock date

    var date_given_to_be_updated = close_date.getTime()
    console.log("date_given_to_be_updated = ",date_given_to_be_updated)

    const current_date = Date.now(); // this is todays date
    console.log("todays date in milliseconds = ",current_date)

    if (current_date > date_given_to_be_updated) {
        console.log("date has already passed")
        return ("datepassed")
    } else {
        console.log("date has not yet passed")
        return ("fail")
    }
};



exports.sql_petition_get = async function(petition_id) {

    const query = "SELECT petition_id AS petitionId, title, Category.name AS category, User.name AS authorName," +
        "(select count(*) from Signature where Signature.petition_id = ?)  " +
        "AS signatureCount , description, author_id AS authorId, city AS authorCity, " +
        "country AS authorCountry, created_date AS createdDate, closing_date AS closingDate " +
        "FROM Petition JOIN User ON Petition.author_id = User.user_id JOIN Category ON Category.category_id " +
        "= Petition.category_id WHERE Petition.petition_id = ?"
    console.log("got here")
    try {
        const conn = await db.getPool().getConnection();
        const values = [petition_id,petition_id]
        const rows = await conn.query(query, values);
        conn.release();
        // console.log("query exists", rows[0])
        // console.log("signatures retrieved");
        return(rows[0][0])
    }catch{
        conn.release();
        console.log("fail")
        return("fail")
    }


};

exports.return_petitions= async function(jsonfile){
    let trip = false;
    let addtion = false;
    let values = [];



    let query = "SELECT Petition.petition_id AS petitionId, Petition.title,Category.name AS category,User.name AS authorName,\n" +
        "count(*) AS signatureCount FROM Signature JOIN Petition ON Petition.petition_id = Signature.petition_id\n" +
        "JOIN Category ON Category.category_id = Petition.category_id JOIN User ON Petition.author_id = User.user_id\n";

    if (jsonfile.hasOwnProperty("categoryId")) {
        // This is a WHERE clause where catagory ID
        const categoryId = jsonfile.categoryId;
        query = query + "WHERE Petition.category_id = ? ";
        //WHERE added at end when constructing
        trip = true;
        values.push(Number(categoryId));
    }

    if (jsonfile.hasOwnProperty("authorId")) {
        // This is a WHERE clause where authorId ID
        const authorId = jsonfile.authorId;
        if(jsonfile.hasOwnProperty("categoryId")){
            query = query + "AND Petition.author_id = ? ";
        }else{
            query = query + "WHERE Petition.author_id = ? ";
        }
        trip = true;
        values.push(Number(authorId));
    }


    if (jsonfile.hasOwnProperty("q")) {
        const q = jsonfile.q;
        if(jsonfile.hasOwnProperty("authorId")||jsonfile.hasOwnProperty("categoryId")){
            query = query + "AND Petition.title LIKE " + "'" + "%" + q + "%" + "' "
        }else{
            query = query +"WHERE Petition.title LIKE " + "'" + "%" + q + "%" + "' "
        }
        trip = true;
    }

    if (jsonfile.hasOwnProperty("sortBy")){
        trip = true;
        let alphabet = false;
        let sortBy = jsonfile.sortBy;
        if(sortBy == "SIGNATURES_ASC"){
            sortBy = "ASC "
        }
        if(sortBy == "SIGNATURES_DESC"){
            sortBy = "DESC "
        }
        if(sortBy == "ALPHABETICAL_ASC"){
            sortBy = "ASC "
            alphabet = true
        }
        if(sortBy == "ALPHABETICAL_DESC"){
            sortBy = "DESC "
            alphabet = true
        }
        if(alphabet == true){
            query = query + "GROUP BY Petition.petition_id ORDER BY Petition.title " + sortBy
        }else{
            query = query + "GROUP BY Petition.petition_id ORDER BY signatureCount " + sortBy
        }

    }else{
        query = query + "GROUP BY Petition.petition_id ORDER BY signatureCount DESC "
        trip = true;
    }

    if (jsonfile.hasOwnProperty("count") && jsonfile.hasOwnProperty("startIndex")){
        const count = jsonfile.count;
        const startIndex = jsonfile.startIndex;
        query = query + "LIMIT ? OFFSET ? ";
        trip = true;
        values.push(Number(count));
        values.push(Number(startIndex));

    }else if(jsonfile.hasOwnProperty("count")){
        const count = jsonfile.count;
        query = query + "LIMIT ? OFFSET 0 ";
        trip = true;
        values.push(Number(count));

    }else if(jsonfile.hasOwnProperty("startIndex")){
        const startIndex = jsonfile.startIndex;
        query = query + "LIMIT 9999999999999 OFFSET ? ";
        trip = true;
        values.push(Number(startIndex));
    }


    if(trip == false){
        console.log("got here")
        query = "SELECT Petition.petition_id AS petitionId, Petition.title,Category.name AS category,User.name AS authorName, count(*) AS signatureCount " +
            "FROM Signature JOIN Petition ON Petition.petition_id = Signature.petition_id JOIN Category ON Category.category_id = Petition.category_id " +
            "JOIN User ON Petition.author_id = User.user_id GROUP BY Petition.petition_id"
            const conn = await db.getPool().getConnection();
            const rows = await conn.query(query);
            conn.release();
            return rows[0]

    }else {

        console.log("query",query)
        console.log("values",values)
        try {
            const conn = await db.getPool().getConnection();
            const rows = await conn.query(query, values); //values
            conn.release();
            return rows[0]
        }catch(err){
            console.log("err",err)
            return("sqlerror")
        }

    }

};


exports.check_catagory_id_exists = async function(id) {
    try {
        const query = 'SELECT * FROM Category WHERE category_id = ?';
        const conn = await db.getPool().getConnection();
        const rows = await conn.query(query, id);
        const exists = rows[0]
        conn.release();
        if(exists.length < 1){
            return("no_id_exists")
        }else{
            return rows[0]
        }
        console.log("result",exists)
    }catch(err){
        console.log("error",err)
    }

};