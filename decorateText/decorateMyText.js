const btnFunction = function() {
  let textArea = document.getElementById("txtArea");
  setInterval(btnHelper, 500, textArea);
  textArea.value = convertTextToPigLatin(textArea.value);
};

const btnHelper = function(textArea) {
  let fontSize = parseInt(
    window.getComputedStyle(textArea, null).getPropertyValue("font-size")
  );
  fontSize += 2;
  textArea.style.fontSize = fontSize + "pt";
};

const cbxFunction = function() {
  const checkBox = document.getElementById("cbx");
  let textArea = document.getElementById("txtArea");
  if (checkBox.checked === true) {
    textArea.className = "decorateTextArea";
  } else {
    textArea.className = "resetTextArea";
  }
  document.getElementsByTagName("body")[0].className = "addBackgroundImg";
};

window.onload = function() {
  document.getElementById("btn").onclick = btnFunction;
  document.getElementById("cbx").onchange = cbxFunction;
};

const convertTextToPigLatin = function(text) {
  const words = text.split(" ");
  let retString = "";
  let decodedString;
  for (let i = 0; i < words.length; i++) {
    word = words[i];
    decodedString = decodeString(word);
    if (decodedString.consonent) {
      word = word.replace(decodedString.consonent, "");
      word = word.concat(decodedString.consonent);
    }
    word = word.concat("ay");
    retString = retString.concat(" ", word);
  }
  return retString;
};

const decodeString = function(word) {
  let consonent = {};
  const vowels = ["a", "e", "i", "o", "u"];
  // check if string starts with vowel or not
  for (let i = 0; i < vowels.length; i++) {
    if (word.startsWith(vowels[i])) return consonent;
  }
  // get the index
  letters = word.split("");
  let count = 0;
  for (let i = 0; i < letters.length; i++) {
    let found = false;
    for (let j = 0; j < vowels.length; j++)
      if (letters[i] === vowels[j]) found = true;
    if (found) break;
    count++;
  }
  consonent.consonent = word.slice(0, count);
  return consonent;
};
