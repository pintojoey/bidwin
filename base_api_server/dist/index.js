'use strict';

var _slicedToArray = function () { function sliceIterator(arr, i) { var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"]) _i["return"](); } finally { if (_d) throw _e; } } return _arr; } return function (arr, i) { if (Array.isArray(arr)) { return arr; } else if (Symbol.iterator in Object(arr)) { return sliceIterator(arr, i); } else { throw new TypeError("Invalid attempt to destructure non-iterable instance"); } }; }();

var _baseClientJs = require("@bitclave/base-client-js");

var _baseClientJs2 = _interopRequireDefault(_baseClientJs);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _asyncToGenerator(fn) { return function () { var gen = fn.apply(this, arguments); return new Promise(function (resolve, reject) { function step(key, arg) { try { var info = gen[key](arg); var value = info.value; } catch (error) { reject(error); return; } if (info.done) { resolve(value); } else { return Promise.resolve(value).then(function (value) { step("next", value); }, function (err) { step("throw", err); }); } } return step("next"); }); }; }

//required for babel to polyfill regeneratorRuntime
require("core-js/modules/es6.typed.array-buffer");

require("core-js/modules/es6.typed.data-view");

require("core-js/modules/es6.typed.int8-array");

require("core-js/modules/es6.typed.uint8-array");

require("core-js/modules/es6.typed.uint8-clamped-array");

require("core-js/modules/es6.typed.int16-array");

require("core-js/modules/es6.typed.uint16-array");

require("core-js/modules/es6.typed.int32-array");

require("core-js/modules/es6.typed.uint32-array");

require("core-js/modules/es6.typed.float32-array");

require("core-js/modules/es6.typed.float64-array");

require("core-js/modules/es6.map");

require("core-js/modules/es6.set");

require("core-js/modules/es6.weak-map");

require("core-js/modules/es6.weak-set");

require("core-js/modules/es6.reflect.apply");

require("core-js/modules/es6.reflect.construct");

require("core-js/modules/es6.reflect.define-property");

require("core-js/modules/es6.reflect.delete-property");

require("core-js/modules/es6.reflect.get");

require("core-js/modules/es6.reflect.get-own-property-descriptor");

require("core-js/modules/es6.reflect.get-prototype-of");

require("core-js/modules/es6.reflect.has");

require("core-js/modules/es6.reflect.is-extensible");

require("core-js/modules/es6.reflect.own-keys");

require("core-js/modules/es6.reflect.prevent-extensions");

require("core-js/modules/es6.reflect.set");

require("core-js/modules/es6.reflect.set-prototype-of");

require("core-js/modules/es6.promise");

require("core-js/modules/es6.symbol");

require("core-js/modules/es6.object.freeze");

require("core-js/modules/es6.object.seal");

require("core-js/modules/es6.object.prevent-extensions");

require("core-js/modules/es6.object.is-frozen");

require("core-js/modules/es6.object.is-sealed");

require("core-js/modules/es6.object.is-extensible");

require("core-js/modules/es6.object.get-own-property-descriptor");

require("core-js/modules/es6.object.get-prototype-of");

require("core-js/modules/es6.object.keys");

require("core-js/modules/es6.object.get-own-property-names");

require("core-js/modules/es6.object.assign");

require("core-js/modules/es6.object.is");

require("core-js/modules/es6.object.set-prototype-of");

require("core-js/modules/es6.function.name");

require("core-js/modules/es6.string.raw");

require("core-js/modules/es6.string.from-code-point");

require("core-js/modules/es6.string.code-point-at");

require("core-js/modules/es6.string.repeat");

require("core-js/modules/es6.string.starts-with");

require("core-js/modules/es6.string.ends-with");

require("core-js/modules/es6.string.includes");

require("core-js/modules/es6.regexp.flags");

require("core-js/modules/es6.regexp.match");

require("core-js/modules/es6.regexp.replace");

require("core-js/modules/es6.regexp.split");

require("core-js/modules/es6.regexp.search");

require("core-js/modules/es6.array.from");

require("core-js/modules/es6.array.of");

require("core-js/modules/es6.array.copy-within");

require("core-js/modules/es6.array.find");

require("core-js/modules/es6.array.find-index");

require("core-js/modules/es6.array.fill");

require("core-js/modules/es6.array.iterator");

require("core-js/modules/es6.number.is-finite");

require("core-js/modules/es6.number.is-integer");

require("core-js/modules/es6.number.is-safe-integer");

require("core-js/modules/es6.number.is-nan");

require("core-js/modules/es6.number.epsilon");

require("core-js/modules/es6.number.min-safe-integer");

require("core-js/modules/es6.number.max-safe-integer");

require("core-js/modules/es6.math.acosh");

require("core-js/modules/es6.math.asinh");

require("core-js/modules/es6.math.atanh");

require("core-js/modules/es6.math.cbrt");

require("core-js/modules/es6.math.clz32");

require("core-js/modules/es6.math.cosh");

require("core-js/modules/es6.math.expm1");

require("core-js/modules/es6.math.fround");

require("core-js/modules/es6.math.hypot");

require("core-js/modules/es6.math.imul");

require("core-js/modules/es6.math.log1p");

require("core-js/modules/es6.math.log10");

require("core-js/modules/es6.math.log2");

require("core-js/modules/es6.math.sign");

require("core-js/modules/es6.math.sinh");

require("core-js/modules/es6.math.tanh");

require("core-js/modules/es6.math.trunc");

require("core-js/modules/es7.array.includes");

require("core-js/modules/es7.object.values");

require("core-js/modules/es7.object.entries");

require("core-js/modules/es7.object.get-own-property-descriptors");

require("core-js/modules/es7.string.pad-start");

require("core-js/modules/es7.string.pad-end");

require("core-js/modules/web.timers");

require("core-js/modules/web.immediate");

require("core-js/modules/web.dom.iterable");

require("regenerator-runtime/runtime");

var express = require('express');
var app = express();

app.post('/store', function (req, res) {
    _asyncToGenerator( /*#__PURE__*/regeneratorRuntime.mark(function _callee() {
        var passphrase, base, keyPair, account, data, encryptedData, _iteratorNormalCompletion, _didIteratorError, _iteratorError, _iterator, _step, _ref2, _ref3, key, value, decryptedData, _iteratorNormalCompletion2, _didIteratorError2, _iteratorError2, _iterator2, _step2, _ref4, _ref5;

        return regeneratorRuntime.wrap(function _callee$(_context) {
            while (1) {
                switch (_context.prev = _context.next) {
                    case 0:
                        console.log(req.params);
                        //Define a passphrase
                        passphrase = 'some passphrase';

                        //Initialize Base

                        base = new _baseClientJs2.default("https://base2-bitclva-com.herokuapp.com", 'localhost', '', '');

                        base.changeStrategy('POSTGRES');

                        //Create a KeyPair
                        _context.next = 6;
                        return base.createKeyPairHelper('').createKeyPair(passphrase);

                    case 6:
                        keyPair = _context.sent;


                        console.log("\nCreated a keypair for the passphrase: " + passphrase);
                        console.log("PublicKey:" + keyPair.publicKey);
                        console.log("PrivateKey:" + keyPair.privateKey);

                        //Check for existence or create a new account
                        account = void 0;
                        _context.prev = 11;

                        console.log("\nChecking if account already exists.");
                        _context.next = 15;
                        return base.accountManager.checkAccount(passphrase, "somemessage");

                    case 15:
                        account = _context.sent;

                        console.log("Account already exists: " + JSON.stringify(account));
                        _context.next = 26;
                        break;

                    case 19:
                        _context.prev = 19;
                        _context.t0 = _context["catch"](11);

                        console.log("\nAccount doesn't exist, Creating a new one.");
                        _context.next = 24;
                        return base.accountManager.registration(passphrase, req.body.name);

                    case 24:
                        account = _context.sent;

                        console.log("Account created:" + JSON.stringify(account));

                    case 26:
                        data = new Map();

                        data.set("id", req.params.id);
                        data.set("name", req.prams.name);
                        data.set("billingInfo", req.params.billingInfo);

                        // Save encrypted data to Base
                        _context.next = 32;
                        return base.profileManager.updateData(data);

                    case 32:
                        encryptedData = _context.sent;

                        console.log("\nUser data is encrypted and saved to Base.");
                        _iteratorNormalCompletion = true;
                        _didIteratorError = false;
                        _iteratorError = undefined;
                        _context.prev = 37;
                        for (_iterator = encryptedData.entries()[Symbol.iterator](); !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
                            _ref2 = _step.value;
                            _ref3 = _slicedToArray(_ref2, 2);
                            key = _ref3[0];
                            value = _ref3[1];

                            console.log("Key:" + key + ", Encrypted Value:" + value);
                        }

                        // Read saved data and decrypt
                        _context.next = 45;
                        break;

                    case 41:
                        _context.prev = 41;
                        _context.t1 = _context["catch"](37);
                        _didIteratorError = true;
                        _iteratorError = _context.t1;

                    case 45:
                        _context.prev = 45;
                        _context.prev = 46;

                        if (!_iteratorNormalCompletion && _iterator.return) {
                            _iterator.return();
                        }

                    case 48:
                        _context.prev = 48;

                        if (!_didIteratorError) {
                            _context.next = 51;
                            break;
                        }

                        throw _iteratorError;

                    case 51:
                        return _context.finish(48);

                    case 52:
                        return _context.finish(45);

                    case 53:
                        _context.next = 55;
                        return base.profileManager.getData();

                    case 55:
                        decryptedData = _context.sent;

                        console.log("\nUser data is retrieved from Base and decrypted.");
                        _iteratorNormalCompletion2 = true;
                        _didIteratorError2 = false;
                        _iteratorError2 = undefined;
                        _context.prev = 60;
                        for (_iterator2 = decryptedData.entries()[Symbol.iterator](); !(_iteratorNormalCompletion2 = (_step2 = _iterator2.next()).done); _iteratorNormalCompletion2 = true) {
                            _ref4 = _step2.value;
                            _ref5 = _slicedToArray(_ref4, 2);
                            key = _ref5[0];
                            value = _ref5[1];

                            console.log("Key:" + key + ", Decrypted Value:" + value);
                        }
                        _context.next = 68;
                        break;

                    case 64:
                        _context.prev = 64;
                        _context.t2 = _context["catch"](60);
                        _didIteratorError2 = true;
                        _iteratorError2 = _context.t2;

                    case 68:
                        _context.prev = 68;
                        _context.prev = 69;

                        if (!_iteratorNormalCompletion2 && _iterator2.return) {
                            _iterator2.return();
                        }

                    case 71:
                        _context.prev = 71;

                        if (!_didIteratorError2) {
                            _context.next = 74;
                            break;
                        }

                        throw _iteratorError2;

                    case 74:
                        return _context.finish(71);

                    case 75:
                        return _context.finish(68);

                    case 76:
                    case "end":
                        return _context.stop();
                }
            }
        }, _callee, this, [[11, 19], [37, 41, 45, 53], [46,, 48, 52], [60, 64, 68, 76], [69,, 71, 75]]);
    }))();

    res.send('Hello World!');
});

app.listen(3000, function () {
    return console.log('Example app listening on port 3000!');
});

process.on('unhandledRejection', function (reason, p) {
    console.log('Unhandled Rejection at: Promise', p, 'reason:', reason);
});