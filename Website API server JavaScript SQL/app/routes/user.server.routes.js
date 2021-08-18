const users = require( '../controllers/users.server.controller' );


module.exports = function( app ){
    app.route(app.rootUrl + '/users/register')
        .post(users.create);

    app.route(app.rootUrl + '/users/login')
        .post(users.login);

    app.route(app.rootUrl + '/users/logout')
        .post(users.logout);

    app.route(app.rootUrl + '/users/:id')
        .get(users.info)
        .patch(users.user_update);

    app.route(app.rootUrl + '/users/:id/photo')
        .get(users.get_photo)
        .put(users.set_photo)
        .delete(users.delete_photo);

    app.route(app.rootUrl + '/petitions')
        .get(users.get_petition)
        .post(users.add_petition);

    app.route(app.rootUrl + '/petitions/:id/photo')
        .get(users.get_petition_photo)
        .put(users.update_petition_photo);

    app.route(app.rootUrl + '/petitions/categories')
        .get(users.get_petition_catagories);

    app.route(app.rootUrl + '/petitions/:id')
        .delete(users.delete_petition)
        .patch(users.update_petition_details)
        .get(users.get_petition_details)

    app.route(app.rootUrl + '/petitions/:id/signatures')
        .get(users.retrieve_signatures)
        .post(users.sign_petition)
        .delete(users.delete_signature);

};