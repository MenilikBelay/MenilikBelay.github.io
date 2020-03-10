(function() {
  // one way to tell if we are playing animations or not
  let intervalObject; // holds the interval object that the player is working on
  let playerObject; // holds the reference to player function (instance at a given time)
  const defaultIntervalDuration = 250;

  const reset = () => {
    intervalObject = undefined;
    playerObject = undefined;
    document.getElementById("animationTypeSelector").options[0].selected = true;
    document.getElementById("canvas").value = ANIMATIONS["BLANK"];
  };

  const player = (animation, delimiter) => {
    const frames = animation.split(delimiter);
    let index = 0;
    return () => {
      index = index === frames.length ? 0 : index;
      displayAnimation(frames[index]);
      index++;
    };
  };

  const play = (speed = defaultIntervalDuration) => {
    // default speed of defaultIntervalDuration ms
    intervalObject = setInterval(playerObject, speed);
  };

  const displayAnimation = text => {
    textArea = document.getElementById("canvas");
    textArea.value = text;
  };

  const preSet = choice => {
    // set the selected choice to to display area
    displayAnimation(ANIMATIONS[choice]);
  };

  const setSpeed = speed => {
    if (intervalObject) {
      // if we have interval already
      clearInterval(intervalObject);
    }
    play(speed);
  };

  const buttonsPattern = () => {
    // button pattern for start and stop
    let startBtn = document.getElementById("startBtn");
    let stopBtn = document.getElementById("stopBtn");
    let animationTypeSelector = document.getElementById(
      "animationTypeSelector"
    );
    if (intervalObject) {
      // disable start, enable stop, disable animation type selector
      startBtn.disabled = true;
      stopBtn.disabled = false;
      animationTypeSelector.disabled = true;
    } else {
      // disable stop, enable start, enable animation type selector
      startBtn.disabled = false;
      stopBtn.disabled = true;
      animationTypeSelector.disabled = false;
    }
  };

  //#region EVENT HANDLERS
  const onSpeedChange = event => {
    console.log(event.target.checked);
    let intervalDuration = event.target.checked ? 50 : defaultIntervalDuration; // set to 50ms or default
    setSpeed(intervalDuration);
  };
  const onAnimationSizeChange = event => {
    document.getElementById("canvas").style.fontSize = event.target.value;
  };
  const onAnimationTypeChange = event => {
    preSet(event.target.value);
  };
  const onStopBtnHandler = () => {
    // clear interval object and set it to undefined
    if (intervalObject) {
      clearInterval(intervalObject);
      reset();
    }
    // switch button pattern
    buttonsPattern();
  };
  const onStartBtnHandler = () => {
    const animation = document.getElementById("canvas").value;
    const delimiter = "=====\n";
    const speed = document.getElementById("turboSpeed").checked
      ? 50
      : defaultIntervalDuration;
    playerObject = player(animation, delimiter);
    intervalObject = setInterval(playerObject, speed);
    buttonsPattern();
  };
  //#endregion EVENT HANDLERS

  window.onload = () => {
    // initialize
    document.getElementById("startBtn").onclick = onStartBtnHandler;
    document.getElementById("stopBtn").onclick = onStopBtnHandler;
    document.getElementById(
      "animationTypeSelector"
    ).onchange = onAnimationTypeChange;
    document.getElementById(
      "animationSizeSelector"
    ).onchange = onAnimationSizeChange;
    document.getElementById("turboSpeed").onchange = onSpeedChange;
  };
})();
