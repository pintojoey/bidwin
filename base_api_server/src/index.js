'use strict';

import Base from "@bitclave/base-client-js";

//required for babel to polyfill regeneratorRuntime
require("babel-polyfill");
const express = require('express')
const app = express()

app.post('/store', (req, res) => {
    (async function() {
        console.log(req.params)
        //Define a passphrase
        const passphrase = 'some passphrase';
        
        //Initialize Base
        const base = new Base("http://localost:8080", 'localhost', '', '');
        base.changeStrategy('POSTGRES');
        
        //Create a KeyPair
        let keyPair = await base.createKeyPairHelper('').createKeyPair(passphrase);
    
        console.log("\nCreated a keypair for the passphrase: " + passphrase);
        console.log("PublicKey:" + keyPair.publicKey);
        console.log("PrivateKey:" + keyPair.privateKey);
    
        //Check for existence or create a new account
        let account;
        try {
            console.log("\nChecking if account already exists.");
            account = await base.accountManager.checkAccount(passphrase, "somemessage");
            console.log("Account already exists: " + JSON.stringify(account));
        } catch(e) {
            console.log("\nAccount doesn't exist, Creating a new one.");
            account = await base.accountManager.registration(passphrase, req.body.name);
            console.log("Account created:" + JSON.stringify(account));
        }
    
        let data = new Map();
        data.set("id", req.body.id);
        data.set("name", req.body.name);
        data.set("billingInfo", req.body.billingInfo);
        
    
        // Save encrypted data to Base
        let encryptedData = await base.profileManager.updateData(data);
        console.log("\nUser data is encrypted and saved to Base.");
        for (var [key, value] of encryptedData.entries()) {
            console.log("Key:" + key + ", Encrypted Value:" + value);
        }
    
        // Read saved data and decrypt
        let decryptedData = await base.profileManager.getData();
        console.log("\nUser data is retrieved from Base and decrypted.");
        for (var [key, value] of decryptedData.entries()) {
            console.log("Key:" + key + ", Decrypted Value:" + value);
        }
    })();
       
        res.send('Hello World!')
    });
    
    app.post('/placeOrder', (req, res) => {
        (async function() {
            console.log(req.params)
            //Define a passphrase
            const passphrase = 'some passphrase';
            
            //Initialize Base
            const base = new Base("http://localost:8080", 'localhost', '', '');
            base.changeStrategy('POSTGRES');
            
            //Create a KeyPair
            let keyPair = await base.createKeyPairHelper('').createKeyPair(passphrase);
        
            console.log("\nCreated a keypair for the passphrase: " + passphrase);
            console.log("PublicKey:" + keyPair.publicKey);
            console.log("PrivateKey:" + keyPair.privateKey);
        
            //Check for existence or create a new account
            let account;
            try {
                console.log("\nChecking if account already exists.");
                account = await base.accountManager.checkAccount(passphrase, "somemessage");
                console.log("Account already exists: " + JSON.stringify(account));
            } catch(e) {
                console.log("\nAccount doesn't exist, Creating a new one.");
                account = await base.accountManager.registration(passphrase, req.body.name);
                console.log("Account created:" + JSON.stringify(account));
            }
        
            let data = new Map();
            data.set("product_id", req.body.product_id);
            data.set("customer_id", req.body.userid);
            data.set("start_bid", req.body.start_bid);
            data.set("buy_now", req.body.buy_now);
            
        
            // Save encrypted data to Base
            let encryptedData = await base.profileManager.updateData(data);
            console.log("\nUser data is encrypted and saved to Base.");
            for (var [key, value] of encryptedData.entries()) {
                console.log("Key:" + key + ", Encrypted Value:" + value);
            }
        
            // Read saved data and decrypt
            let decryptedData = await base.profileManager.getData();
            console.log("\nUser data is retrieved from Base and decrypted.");
            for (var [key, value] of decryptedData.entries()) {
                console.log("Key:" + key + ", Decrypted Value:" + value);
            }
        })();
           
            res.send('Hello World!')
        });

        app.post('/placeOrder', (req, res) => {
            (async function() {
                console.log(req.params)
                //Define a passphrase
                const passphrase = 'some passphrase';
                
                //Initialize Base
                const base = new Base("http://localost:8080", 'localhost', '', '');
                base.changeStrategy('POSTGRES');
                
                //Create a KeyPair
                let keyPair = await base.createKeyPairHelper('').createKeyPair(passphrase);
            
                console.log("\nCreated a keypair for the passphrase: " + passphrase);
                console.log("PublicKey:" + keyPair.publicKey);
                console.log("PrivateKey:" + keyPair.privateKey);
            
                //Check for existence or create a new account
                let account;
                try {
                    console.log("\nChecking if account already exists.");
                    account = await base.accountManager.checkAccount(passphrase, "somemessage");
                    console.log("Account already exists: " + JSON.stringify(account));
                } catch(e) {
                    console.log("\nAccount doesn't exist, Creating a new one.");
                    account = await base.accountManager.registration(passphrase, req.body.name);
                    console.log("Account created:" + JSON.stringify(account));
                }
            
                let data = new Map();
                data.set("product_id", req.body.product_id);
                data.set("customer_id", req.body.userid);
                data.set("start_bid", req.body.start_bid);
                data.set("buy_now", req.body.buy_now);
                
            
                // Save encrypted data to Base
                let encryptedData = await base.profileManager.updateData(data);
                console.log("\nUser data is encrypted and saved to Base.");
                for (var [key, value] of encryptedData.entries()) {
                    console.log("Key:" + key + ", Encrypted Value:" + value);
                }
            
                // Read saved data and decrypt
                let decryptedData = await base.profileManager.getData();
                console.log("\nUser data is retrieved from Base and decrypted.");
                for (var [key, value] of decryptedData.entries()) {
                    console.log("Key:" + key + ", Decrypted Value:" + value);
                }
            })();
               
                res.send('Hello World!')
            });

            app.post('/placeBid', (req, res) => {
                (async function() {
                    console.log(req.params)
                    //Define a passphrase
                    const passphrase = 'some passphrase';
                    
                    //Initialize Base
                    const base = new Base("http://localost:8080", 'localhost', '', '');
                    base.changeStrategy('POSTGRES');
                    
                    //Create a KeyPair
                    let keyPair = await base.createKeyPairHelper('').createKeyPair(passphrase);
                
                    console.log("\nCreated a keypair for the passphrase: " + passphrase);
                    console.log("PublicKey:" + keyPair.publicKey);
                    console.log("PrivateKey:" + keyPair.privateKey);
                
                    //Check for existence or create a new account
                    let account;
                    try {
                        console.log("\nChecking if account already exists.");
                        account = await base.accountManager.checkAccount(passphrase, "somemessage");
                        console.log("Account already exists: " + JSON.stringify(account));
                    } catch(e) {
                        console.log("\nAccount doesn't exist, Creating a new one.");
                        account = await base.accountManager.registration(passphrase, req.body.name);
                        console.log("Account created:" + JSON.stringify(account));
                    }
                
                    let data = new Map();
                    data.set("order_id", req.body.order_id);
                    data.set("price", req.body.price);
                    data.set("inventory_id", req.body.inventory_id);
                    
                
                    // Save encrypted data to Base
                    let encryptedData = await base.profileManager.updateData(data);
                    console.log("\nUser data is encrypted and saved to Base.");
                    for (var [key, value] of encryptedData.entries()) {
                        console.log("Key:" + key + ", Encrypted Value:" + value);
                    }
                
                    // Read saved data and decrypt
                    let decryptedData = await base.profileManager.getData();
                    console.log("\nUser data is retrieved from Base and decrypted.");
                    for (var [key, value] of decryptedData.entries()) {
                        console.log("Key:" + key + ", Decrypted Value:" + value);
                    }
                })();
                   
                    res.send('Hello World!')
                });

                app.get('/bid', (req, res) => {
                    (async function() {
                        console.log(req.params)
                        //Define a passphrase
                        const passphrase = 'some passphrase';
                        
                        //Initialize Base
                        const base = new Base("http://localost:8080", 'localhost', '', '');
                        base.changeStrategy('POSTGRES');
                        
                        //Create a KeyPair
                        let keyPair = await base.createKeyPairHelper('').createKeyPair(passphrase);
                    
                        console.log("\nCreated a keypair for the passphrase: " + passphrase);
                        console.log("PublicKey:" + keyPair.publicKey);
                        console.log("PrivateKey:" + keyPair.privateKey);
                    
                        //Check for existence or create a new account
                        let account;
                        try {
                            console.log("\nChecking if account already exists.");
                            account = await base.accountManager.checkAccount(passphrase, "somemessage");
                            console.log("Account already exists: " + JSON.stringify(account));
                        } catch(e) {
                            console.log("\nAccount doesn't exist, Creating a new one.");
                            account = await base.accountManager.registration(passphrase, req.body.order_id);
                            console.log("Account created:" + JSON.stringify(account));
                        }
                    
                    
                        // Read saved data and decrypt
                        let decryptedData = await base.profileManager.getData();
                        console.log("\nUser data is retrieved from Base and decrypted.");
                        for (var [key, value] of decryptedData.entries()) {
                            console.log("Key:" + key + ", Decrypted Value:" + value);
                        }
                    })();
                       
                        res.send('Hello World!')
                    });


                    app.get('/orders', (req, res) => {
                        (async function() {
                            console.log(req.params)
                            //Define a passphrase
                            const passphrase = 'some passphrase';
                            
                            //Initialize Base
                            const base = new Base("http://localost:8080", 'localhost', '', '');
                            base.changeStrategy('POSTGRES');
                            
                            //Create a KeyPair
                            let keyPair = await base.createKeyPairHelper('').createKeyPair(passphrase);
                        
                            console.log("\nCreated a keypair for the passphrase: " + passphrase);
                            console.log("PublicKey:" + keyPair.publicKey);
                            console.log("PrivateKey:" + keyPair.privateKey);
                        
                            //Check for existence or create a new account
                            let account;
                            try {
                                console.log("\nChecking if account already exists.");
                                account = await base.accountManager.checkAccount(passphrase, "somemessage");
                                console.log("Account already exists: " + JSON.stringify(account));
                            } catch(e) {
                                console.log("\nAccount doesn't exist, Creating a new one.");
                                account = await base.accountManager.registration(passphrase, req.body.product_id);
                                console.log("Account created:" + JSON.stringify(account));
                            }
                        
                        
                            // Read saved data and decrypt
                            let decryptedData = await base.profileManager.getData();
                            console.log("\nUser data is retrieved from Base and decrypted.");
                            for (var [key, value] of decryptedData.entries()) {
                                console.log("Key:" + key + ", Decrypted Value:" + value);
                            }
                        })();
                           
                            res.send('Hello World!')
                        });


app.listen(3000, () => console.log('Example app listening on port 3000!'))

process.on('unhandledRejection', (reason, p) => {
    console.log('Unhandled Rejection at: Promise', p, 'reason:', reason);
});

