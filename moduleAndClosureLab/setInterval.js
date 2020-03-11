const rudyTimer = (function() {
  let timer = null; // stores ID of interval timer
  function rudy() {
    // called each time the timer goes off
    document.getElementById("output").innerHTML += " Rudy!";
  }
  return function delayMsg2() {
    if (timer === null) {
      timer = setInterval(rudy, 1000);
    } else {
      clearInterval(timer);
      timer = null;
    }
  };
})();

window.onload = () => {
  document.getElementById("btnClickMe").onclick = rudyTimer;
};
