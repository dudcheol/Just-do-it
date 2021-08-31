var a = [1, function () {}, 2, function () {}];

console.log(JSON.stringify(a)); // [1,null,2,null]

console.log(
  JSON.stringify(a, function (key, value) {
    if (typeof value == 'function') return !!value;
    else return value;
  })
); // [1,true,2,true]
