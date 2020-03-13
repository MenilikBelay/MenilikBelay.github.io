const loss = (() => {
  let errors = 0; // losing points
  return {
    add: () => {
      errors++;
    },
    getErrors: () => errors,
    reset: () => {
      errors = 0;
    },
    getResult: () => (errors !== 0 ? "Sorry, you lose" : "You win")
  };
})();

const displayResult = message => {
  $("#status").html(message);
};

$(document).ready(() => {
  $(".boundary").mouseover(function() {
    $(this).addClass("youlose");
    loss.add();
  });
  $("#end").mouseover(function() {
    displayResult(loss.getResult());
  });
  $("#start").click(function() {
    $(".boundary").removeClass("youlose");
    loss.reset();
    displayResult("Playing...");
  });
  $("div#maze").on("mouseleave", function() {
    $(".boundary").addClass("youlose");
    loss.add();
    displayResult(loss.getResult());
  });
});
