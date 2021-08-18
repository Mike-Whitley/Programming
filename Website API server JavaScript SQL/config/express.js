const express = require('express');
const bodyParser = require('body-parser');
const { allowCrossOriginRequestsMiddleware } = require('../app/middleware/cors.middleware');


module.exports = function () {
    // INITIALISE EXPRESS //
    const app = express();
    app.rootUrl = '/api/v1';

    // MIDDLEWARE
    app.use(allowCrossOriginRequestsMiddleware);
    app.use(bodyParser.json());
    app.use(bodyParser.raw({limit: '50mb', type: 'text/plain' }));  // for the /executeSql endpoint
    app.use(bodyParser.raw({limit: '50mb', type: 'image/png' }));  // for the /executeSql endpoint
    app.use(bodyParser.raw({limit: '50mb', type: 'image/jpeg' }));  // for the /executeSql endpoint
    app.use(bodyParser.raw({limit: '50mb', type: 'image/gif' }));  // for the /executeSql endpoint
    app.use(bodyParser.raw({limit: '50mb', type: 'image/jpg' }));  // for the /executeSql endpoint
    app.use(bodyParser.raw({limit: '50mb', type: 'image/BMP' }));  // for the /executeSql endpoint
    app.use(bodyParser.raw({limit: '50mb', type: 'image/bmp' }));  // for the /executeSql endpoint
    app.use(bodyParser.raw({limit: '50mb', type: 'image/tiff' }));  // for the /executeSql endpoint
    app.use(bodyParser.raw({limit: '50mb', type: 'image/sbg+xml' }));  // for the /executeSql endpoint
    app.use(bodyParser.raw({limit: '50mb', type: 'image/pjpeg' }));  // for the /executeSql endpoint


    // DEBUG (you can remove these)
    app.use((req, res, next) => {
        console.log(`##### ${req.method} ${req.path} #####`);
        next();
    });

    app.get('/', function (req, res) {
        res.send({ 'message': 'Hello World!' })
    });

    // ROUTES
    require('../app/routes/backdoor.routes')(app);
    require('../app/routes/user.server.routes')(app); // this line has been added after the fact could be wrong

    return app;
};
