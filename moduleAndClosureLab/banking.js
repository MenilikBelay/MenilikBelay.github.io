function Account() {
  let _name;
  let _deposit;
  this.create = (name, deposit) => {
    _name = name;
    _deposit = deposit;
  };
  this.display = () => {
    return "Account name: " + _name + " Balance: " + _deposit;
  };
}

let accountInfoList = [];

const onCreateAccountHandler = () => {
  const populateTextArea = list => {
    let text = "";
    for (let i = 0; i < list.length; i++) {
      text += list[i].display();
      text += "\n";
    }
    document.getElementById("console").value = text;
  };
  let account = new Account();
  account.create(
    document.getElementById("name").value,
    document.getElementById("deposit").value
  );
  accountInfoList.push(account);
  populateTextArea(accountInfoList);
};

window.onload = () => {
  document.getElementById("btnCreate").onclick = onCreateAccountHandler;
};
