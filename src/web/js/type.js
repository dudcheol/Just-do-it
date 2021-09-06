var a = Number('42');
var b = new Number('42');

console.log(a);
console.log(b);

var c = '0';
var d = [];
var e = {};

console.log(Boolean(c), Boolean(d), Boolean(e));

var f = '';
var g = 0;
var h = null;
var i = undefined;

console.log(Boolean(f), Boolean(g), Boolean(h), Boolean(i));
// 모든 객체는 truthy 하다
console.log(
  Boolean(new Boolean(f)),
  Boolean(new Boolean(g)),
  Boolean(new Boolean(h)),
  Boolean(new Boolean(i))
);

// bolean의 명시적 강제변환은 !!를 사용
console.log(!!f, !!g, !!h, !!i);
