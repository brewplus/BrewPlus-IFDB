/* --- Script � 2005-2007 EC Software --- */
var ua = navigator.userAgent;
var dom = (document.getElementById) ? true : false;
var ie4 = (document.all && !dom) ? true : false;
var ie5_5 = ((ua.indexOf("MSIE 5.5")>=0 || ua.indexOf("MSIE 6")>=0) && ua.indexOf("Opera")<0) ? true : false;
var ns4 = (document.layers && !dom) ? true : false;
var offsxy = 6;
function hmshowPopup(e, txt, stick) {
  var tip = '<table  cellpadding="6" cellspacing="0" style="background-color:#FFFFFF; border:1px solid #000000; border-collapse:collapse;"><tr valign=top><td>'+ txt + '<\/td><\/tr><\/table>';
  var tooltip = atooltip();
  e = e?e:window.event;
  var mx = ns4 ? e.PageX : e.clientX;
  var my = ns4 ? e.PageY : e.clientY;
  var obj   = (window.document.compatMode && window.document.compatMode == "CSS1Compat") ? window.document.documentElement : window.document.body;
  var bodyl = (window.pageXOffset) ? window.pageXOffset : obj.scrollLeft;
  var bodyt = (window.pageYOffset) ? window.pageYOffset : obj.scrollTop;
  var bodyw = (window.innerWidth)  ? window.innerWidth  : obj.offsetWidth;
  if (ns4) {
    tooltip.document.write(tip);
    tooltip.document.close();
    if ((mx + offsxy + bodyl + tooltip.width) > bodyw) { mx = bodyw - offsxy - bodyl - tooltip.width; if (mx < 0) mx = 0; }
    tooltip.left = mx + offsxy + bodyl;
    tooltip.top = my + offsxy + bodyt;
  }
  else {
    tooltip.innerHTML = tip;
    if (tooltip.offsetWidth) if ((mx + offsxy + bodyl + tooltip.offsetWidth) > bodyw) { mx = bodyw - offsxy - bodyl - tooltip.offsetWidth; if (mx < 0) mx = 0; }
    tooltip.style.left = (mx + offsxy + bodyl)+"px";
    tooltip.style.top  = (my + offsxy + bodyt)+"px";
  }
  with(tooltip) { ns4 ? visibility="show" : style.visibility="visible" }
  if (stick) document.onmouseup = hmhidePopup;
}
function hmhidePopup() {
  var tooltip = atooltip();
  ns4 ? tooltip.visibility="hide" : tooltip.style.visibility="hidden";
}
function atooltip(){
 return ns4 ? document.hmpopupDiv : ie4 ? document.all.hmpopupDiv : document.getElementById('hmpopupDiv')
}
