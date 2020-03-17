let openSpaceLocation = {
  top: 300,
  left: 300
};
const TILE_SIZE = 100; // tile is 100 x 100 px size
const SIZE = 4; // size of matrics (4 x 4) puzzle

/**
 * Initialization code
 */
const init = function() {
  const puzzleArea = $("#puzzlearea");
  const divs = puzzleArea.find("div");

  // initialize each piece
  for (let i = 0; i < divs.length; i++) {
    let div = divs[i];

    // calculate x and y for this piece
    let x = (i % 4) * 100;
    let y = Math.floor(i / 4) * 100;

    // set basic style and background
    $(div).addClass("puzzlepiece");
    $(div).css({
      "background-image": 'url("static/background.jpg")',
      "background-position": -x + "px " + -y + "px",
      left: x + "px",
      top: y + "px"
    });

    // store x and y for later
    div.x = x;
    div.y = y;
  }
};

/**
 * Sort the siblings of the given node; return
 * an object containing prev and next items for
 * a given node just as nextAll and prevAll work
 * @param {*} nodeList The node
 */
const sortNodes = nodeList => {
  let siblings = {
    prev: [],
    next: []
  };
  let sortedNodes = $(nodeList).siblings();
  sortedNodes.sort((a, b) => {
    // sort nodes in place
    const aTop = parseInt($(a).position().top);
    const aLeft = parseInt($(a).position().left);
    const bTop = parseInt($(b).position().top);
    const bLeft = parseInt($(b).position().left);
    return aTop - bTop !== 0 ? aTop - bTop : aLeft - bLeft;
  });
  // locate the required node
  for (let i = 0; i < sortedNodes.length; i++) {
    if (
      $(nodeList).position().top > $(sortedNodes[i]).position().top ||
      ($(nodeList).position().top === $(sortedNodes[i]).position().top &&
        $(nodeList).position().left > $(sortedNodes[i]).position().left)
    ) {
      siblings.prev.push(sortedNodes[i]); // reverse order
    } else {
      siblings.next.push(sortedNodes[i]); // in order
    }
  }
  siblings.prev.reverse();
  return siblings;
};

/**
 * Finds if an element has free space next to it in any of [left, right, top, bottom]
 * @param {element to check neighboring empty space} node
 * Return object with left and top attributes. If object is empty,
 * it means no opening is found for the given node
 */
const getEmptyArea = node => {
  let openSpace = {}; // object to be returned [holds the open space left & top]
  // get list of all neighbors
  const siblings = sortNodes(node);
  const prev = siblings.prev;
  const next = siblings.next;
  //#region LEFT/RIGHT
  // Check if we have sufficient left and right nodes on the same row
  let count = 0;
  for (let i = 0; i < 3; i++) {
    // count left
    if (!prev[i]) break;
    else if ($(prev[i]).position().top === $(node).position().top) count++;
    else break;
  }
  for (let i = 0; i < 3; i++) {
    // count right
    if (!next[i]) break;
    else if ($(next[i]).position().top === $(node).position().top) count++;
    else break;
  }
  // check if we have opening to the left or right
  if (count != 3) {
    // we have opening on the same row, but where; check
    if (
      !prev[0] ||
      ($(prev[0]).position().top === $(node).position().top &&
        parseInt($(prev[0]).position().left) + 200 ===
          parseInt($(node).position().left))
    ) {
      // we have opening before the node
      openSpace.top = $(node).position().top + "px";
      if (!prev[0]) {
        // means the node is the first element; only next to the empty space
        openSpace.left = 0 + "px";
      } else openSpace.left = parseInt($(prev[0]).position().left) + 100 + "px";
    } else if (
      parseInt($(prev[0]).position().top) + 100 ===
        parseInt($(node).position().top) &&
      parseInt($(prev[0]).position().left) - 200 ===
        parseInt($(node).position().left)
    ) {
      openSpace.top = $(node).position().top;
      openSpace.left = parseInt($(node).position().left) - 100 + "px";
    }
    if (
      !next[0] ||
      ($(next[0]).position().top === $(node).position().top &&
        parseInt($(next[0]).position().left) - 200 ===
          parseInt($(node).position().left))
    ) {
      // we have opening after the node
      openSpace.top = $(node).position().top + "px";
      if (!next[0]) {
        // means the node is the last element; only before the empty space
        openSpace.left = 300 + "px";
      } else openSpace.left = parseInt($(next[0]).position().left) - 100 + "px";
    } else if (
      parseInt($(next[0]).position().top) - 100 ===
        parseInt($(node).position().top) &&
      parseInt($(next[0]).position().left) + 200 ===
        parseInt($(node).position().left)
    ) {
      openSpace.top = $(node).position().top;
      openSpace.left = parseInt($(node).position().left) + 100 + "px";
    }
    return openSpace; // return the location of open space [empty if we have no place]
  }
  //#endregion
  //#region TOP/BOTTOM
  // now to top & bottom part
  let top = prev[3];
  if (!top) {
    // top is null
    top = prev[2]; // update to get location
    if (top) {
      // there is an element 2 nodes back; check if there is open spot
      if (
        parseInt($(top).position().left) - 100 ===
        parseInt($(node).position().left)
      ) {
        // yes we have opening
        openSpace.top = $(top).position().top + "px";
        openSpace.left = $(node).position().left + "px";
        return openSpace;
      } // else we have no opening above
    } // else we have no opening above
  } else {
    // prev[3] is not null
    if (
      parseInt($(top).position().left) + 100 ===
      parseInt($(node).position().left)
    ) {
      openSpace.top = $(top).position().top + "px";
      openSpace.left = $(node).position().left + "px";
      return openSpace;
    } else if (
      parseInt($(top).position().top) + 200 ===
      parseInt($(node).position().top)
    ) {
      openSpace.top = parseInt($(node).position().top) - 100 + "px";
      openSpace.left = $(node).position().left + "px";
      return openSpace;
    } // else we have no opening above
  }
  let bottom = next[3]; // get bottom
  if (!bottom) {
    // bottom is null
    bottom = next[2]; // update to get location
    if (bottom) {
      // there is an element 2 nodes forward; check if there is open spot
      if (
        parseInt($(bottom).position().left) + 100 ===
        parseInt($(node).position().left)
      ) {
        // yes we have opening
        openSpace.top = $(bottom).position().top + "px";
        openSpace.left = $(node).position().left + "px";
        return openSpace;
      } // else we have no opening below
    } // else we have no opening below
  } else {
    // prev[3] is not null
    if (
      parseInt($(bottom).position().left) - 100 ===
      parseInt($(node).position().left)
    ) {
      openSpace.top = $(bottom).position().top + "px";
      openSpace.left = $(node).position().left + "px";
      return openSpace;
    } else if (
      parseInt($(bottom).position().top) - 200 ===
      parseInt($(node).position().top)
    ) {
      openSpace.top = parseInt($(node).position().top) + 100 + "px";
      openSpace.left = $(node).position().left + "px";
      return openSpace;
    } // else we have no opening below
  }
  //#endregion
  return openSpace;
};

const swapPieceWithEmptyArea = (piece, emptyArea) => {
  $(piece).css({ top: emptyArea.top, left: emptyArea.left });
};

/**
 * On page ready, execute ...
 */
$(window).ready(function() {
  init();
  $("#puzzlearea > div").click(function() {
    const emptyArea = getEmptyArea(this);
    if (emptyArea.left) {
      // we have empty neighbor
      swapPieceWithEmptyArea(this, emptyArea);
    }
  });
  $("#puzzlearea > div").hover(
    function() {
      const emptyArea = getEmptyArea(this);
      if (emptyArea.left) {
        $(this).addClass("movablepiece");
      }
    },
    function() {
      // do nothing; well performed in css
    }
  );
});
