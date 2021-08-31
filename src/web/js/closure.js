function closure() {
  let a = 0;
  return function () {
    return ++a;
  };
}

let callClosure = closure();

console.log(callClosure());
console.log(callClosure());
console.log(callClosure());
