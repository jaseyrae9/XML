const express = require('express');
const app = express();
const admin = require('firebase-admin');
const serviceAccount = require('./XML.json');

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

// Za firestore
admin.initializeApp({ 
	credential: admin.credential.applicationDefault()
});

const db = admin.firestore();

function readFire() {
	db.collection('recensions').get().then(snapshot => {
	  snapshot.forEach(doc => {
		console.log(doc.id, '=>', doc.data());
	  });
	})
	.catch(err => {
	  console.log('Error getting documents', err);
	});
}

readFire();

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});

app.post('/recension', function (req, res) {   

	const ref = db.collection('recensions').doc();
	const recension = { 
		id: ref.id,
		rating: req.body.rating,
		title: req.body.title,
		comment: req.body.comment,
		username: req.body.username,
		isApproved: false,
		reservationId: req.body.reservationId,
		roomId: req.body.roomId,
		hotelId: req.body.hotelId,
		creationDate: new Date(),
		modificationDate: new Date()
	} 

	console.log('U funkciji' + req.body.roomId);
	db.collection('ratings').doc(req.body.roomId.toString()).get().then(doc => {
		if (!doc.exists) {
			console.log('Ne postoje rejtinzi za tu sobu, prave se!');
			
			const rating = {
				roomId: req.body.roomId,
				numberOfRatings: 1,
				totalRating: req.body.rating,
				modificationDate: new Date()
			}

			db.collection('ratings').doc(req.body.roomId.toString()).set(rating);
		} else {
			console.log('Postoje rejtinzi za tu sobu');
			console.log('Document data:', doc.data());

			db.collection('ratings').doc(req.body.roomId.toString()).update({
				numberOfRatings: doc.data().numberOfRatings+1, totalRating: doc.data().totalRating + req.body.rating, modificationDate : new Date()
			});
		}
	})
	
	db.collection('recensions').doc(ref.id).set(recension);
	res.send(recension);
});

app.get('/rating/:date', function (req, res) {  

	let ratingRef = db.collection('ratings');
	ratingRef.where('modificationDate', '>', new Date(req.params.date)).get().then(snapshot => {
		if (snapshot.empty) {
			console.log('No matching documents.');
			res.status(404).send('Ne postoje nove ocene!');
			return;
		}  
		let retVal = [];
		snapshot.docs.forEach(function(rating) {
			let tmp = rating.data();
			tmp.modificationDate = rating.get('modificationDate').toDate();
			retVal.push(tmp);
		});
		res.json(retVal);
	});
});


app.put('/recension/:id', function (req, res) {

	db.collection('recensions').doc(req.params.id).get().then(doc => {
		if (!doc.exists) {
			console.log('No such document!');
			res.status(404).send('Recenzija sa zadatim identifikatorom ne postoji!');
			return;
		} else {
			console.log('Document data:', doc.data());
		}
	})
	.catch(err => {
		console.log('Error getting document', err);
	});

	db.collection('recensions').doc(req.params.id).update({
		isApproved: true, modificationDate : new Date()
	});

	res.status(200).send(true);
});

// Kada admin preuzima sve recenzije
app.get('/recension', (req, res, next) => {

	let recensionsRef = db.collection('recensions');
	recensionsRef.where('isApproved', '==', false).get().then(snapshot => {
		if (snapshot.empty) {
			console.log('No matching documents.');
			return;
		}  
		let retVal = [];
		snapshot.docs.forEach(function(recension) {
			let tmp = recension.data();
			tmp.creationDate = recension.get('creationDate').toDate();
			retVal.push(tmp);
		});
		res.json(retVal);


		// res.json(snapshot.docs.map(doc => doc.data()));
	});
});

// Recenzije se vracaju samo za odredjeni smestaj
app.get('/approvedRecensions/:id', (req, res) => {

	let retRecensions = [];
	let totalRating = 0;
	let ratingCount = 0;

	let xmlRef = db.collection('recensions');

	xmlRef.where('roomId', '==', parseInt(req.params.id)).get().then(snapshot => {
		if (snapshot.empty) {
			console.log('No matching documents.');
			res.status(404).send('Trazena soba ne postoji!');
			return;
		}  
		
		snapshot.docs.forEach(function(recension) {
			if(recension.get('isApproved')) {
				let tmp = recension.data();
				tmp.creationDate = recension.get('creationDate').toDate();
				retRecensions.push(tmp);
			}
			ratingCount+=1;
			totalRating+=recension.get('rating');
		});
		res.json({retRecensions, ratingCount, totalRating});
	});	
    
});

app.get('/recension/:hotelId/:date', (req, res) => {
	let xmlRef = db.collection('recensions');
	xmlRef.where('hotelId', '==', parseInt(req.params.hotelId)).get().then(snapshot => {
		if (snapshot.empty) {
			console.log('No matching documents.');
			return;
		}  
		let retVal = [];
		snapshot.docs.forEach(function(recension) {
			if(recension.get('modificationDate').toDate() > new Date(req.params.date)) {
				let tmp = recension.data();
				tmp.creationDate = recension.get('creationDate').toDate();
				retVal.push(tmp);
			}
		});
		res.json(retVal);
	});	
});

module.exports = {
    app
};