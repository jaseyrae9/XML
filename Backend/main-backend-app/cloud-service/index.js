const express = require('express');
const app = express();

'use strict';
// Imports the Google Cloud client library
const {Storage} = require('@google-cloud/storage');
 
var config = {
	projectId: 'xmlprojekat',
	keyFilename: 'XML.json'
};
// Creates a client
var storage = new Storage(config);


app.use(express.json());

const PORT = process.env.PORT || 5555;

let recensions = {};

async function readFile() {
	console.log('Reading File');
	var archivo = storage.bucket('recensions').file('recensions.json').createReadStream();
	console.log('Concat Data');
	var  buf = '';
	archivo.on('data', function(d) {
	  buf += d;
	}).on('end', function() {
	  console.log(buf);
	  recensions = JSON.parse(buf);
	});     

};

async function writeFile() {
	console.log('Writing File');
	var gcsStream = storage.bucket('recensions').file('recensions.json').createWriteStream();
	gcsStream.write('[');

	Object.values( recensions ).forEach(function(i, idx, array){
		if (idx === array.length - 1){ 
			var str = JSON.stringify( i );
			gcsStream.write( str + '\n' );
		}
		else {
			var str = JSON.stringify( i );
			gcsStream.write( str + ',\n' );
		}
	 });
	gcsStream.write(']');
	gcsStream.on('error', (err) => {
		console.error(`${ this.archive }: Error storage file write.`);
		console.error(`${ this.archive }: ${JSON.stringify(err)}`);
	});
	gcsStream.end();
}

readFile();

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});

/**
 * Ocena je vidljiva odmah, dok je komentar vidljiv administratorima i agentima,
 * a pregleda je administrator i odobrava njeno publikovanje (postaje vidljiva i drugim korisnicima).
 */

// Kreiranje recenzije
app.post('/recensions', function (req, res) {   
	/*if(!req.body.rating || !req.body.comment) {
		res.status(400).send('Rating and comment are required!');
		return;
	}*/

	const recension = { 
		id: recensions.length + 1,
		rating: req.body.rating,
		comment: req.body.comment,
		isApproved: false,
		reservationId: req.body.reservationId,
		roomId: req.body.roomId,
		hotelId: req.body.hotelId,
		creationDate: new Date(),
		modificationDate: new Date()
	} 
	recensions.push(recension);
	writeFile();
	res.send(recension);
});

// Odobravanje recenzije, komentar postaje vidljiv i drugim korisnicima
app.put('/recensions/:id', function (req, res) {
	// Look up the recension
	// If not existing, return 404
	const recension = recensions.find(c => c.id === parseInt(req.params.id));
	if(!recension) res.status(404).send('Recenzija sa zadatim identifikatorom ne postoji!');
	
	// Validate
	if(recension.approved === true) {
		res.status(400).send('Recenzija je vec odobrena!');
	}
	
	// Update recension
	recension.isApproved = true;
	// Return the updated recension
	
	res.send(recension);
});

// Kada admin preuzima sve recenzije
app.get('/recensions', (req, res, next) => {
    res.json(recensions);
});

// Recenzije se vracaju samo za odredjeni smestaj
app.get('/approvedRecensions/:id', (req, res) => {
	retVal = recensions.filter(recension => recension.roomId === parseInt(req.params.id) && recension.approved === true);
	// treba izabrati samo one koje su odobrene
    res.json(retVal);
});

/*app.get('/users/:userId', (req, res, next) => {
    res.json(USERS.find(user => user.id === parseInt(req.params.userId)));
});*/

module.exports = {
    app
};