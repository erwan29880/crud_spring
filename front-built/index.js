const express = require('express'); 
const app = express();

app.use('/', express.static('public', { redirect: false }));

app.listen(4200, ()=>{console.log('port 4200')});