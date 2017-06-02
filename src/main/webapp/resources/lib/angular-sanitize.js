
/*
 AngularJS v1.6.5-build.5390+sha.a86a319
 (c) 2010-2017 Google, Inc. http://angularjs.org
 License: MIT
*/
(function(s,e){'use strict';function J(e){var l=[];v(l,B).chars(e);return l.join("")}var w=e.$$minErr("$sanitize"),C,l,D,E,q,B,F,G,v;e.module("ngSanitize",[]).provider("$sanitize",function(){function k(a,f){var b={},c=a.split(","),h;for(h=0;h<c.length;h++)b[f?q(c[h]):c[h]]=!0;return b}function K(a){for(var f={},b=0,c=a.length;b<c;b++){var h=a[b];f[h.name]=h.value}return f}function H(a){return a.replace(/&/g,"&amp;").replace(L,function(a){var b=a.charCodeAt(0);a=a.charCodeAt(1);return"&#"+(1024*(b-
55296)+(a-56320)+65536)+";"}).replace(M,function(a){return"&#"+a.charCodeAt(0)+";"}).replace(/</g,"&lt;").replace(/>/g,"&gt;")}function I(a){for(;a;){if(a.nodeType===s.Node.ELEMENT_NODE)for(var f=a.attributes,b=0,c=f.length;b<c;b++){var h=f[b],d=h.name.toLowerCase();if("xmlns:ns1"===d||0===d.lastIndexOf("ns1:",0))a.removeAttributeNode(h),b--,c--}(f=a.firstChild)&&I(f);a=t("nextSibling",a)}}function t(a,f){var b=f[a];if(b&&F.call(f,b))throw w("elclob",f.outerHTML||f.outerText);return b}var x=!1;this.$get=
["$$sanitizeUri",function(a){x&&l(p,z);return function(f){var b=[];G(f,v(b,function(b,h){return!/^unsafe:/.test(a(b,h))}));return b.join("")}}];this.enableSvg=function(a){return E(a)?(x=a,this):x};C=e.bind;l=e.extend;D=e.forEach;E=e.isDefined;q=e.$$lowercase;B=e.noop;G=function(a,f){null===a||void 0===a?a="":"string"!==typeof a&&(a=""+a);g.innerHTML=a;var b=5;do{if(0===b)throw w("uinput");b--;s.document.documentMode&&I(g);a=g.innerHTML;g.innerHTML=a}while(a!==g.innerHTML);for(b=g.firstChild;b;){switch(b.nodeType){case 1:f.start(b.nodeName.toLowerCase(),
K(b.attributes));break;case 3:f.chars(b.textContent)}var c;if(!(c=b.firstChild)&&(1===b.nodeType&&f.end(b.nodeName.toLowerCase()),c=t("nextSibling",b),!c))for(;null==c;){b=t("parentNode",b);if(b===g)break;c=t("nextSibling",b);1===b.nodeType&&f.end(b.nodeName.toLowerCase())}b=c}for(;b=g.firstChild;)g.removeChild(b)};v=function(a,f){var b=!1,c=C(a,a.push);return{start:function(a,d){a=q(a);!b&&A[a]&&(b=a);b||!0!==p[a]||(c("<"),c(a),D(d,function(b,d){var e=q(d),g="img"===a&&"src"===e||"background"===
e;!0!==u[e]||!0===n[e]&&!f(b,g)||(c(" "),c(d),c('="'),c(H(b)),c('"'))}),c(">"))},end:function(a){a=q(a);b||!0!==p[a]||!0===d[a]||(c("</"),c(a),c(">"));a==b&&(b=!1)},chars:function(a){b||c(H(a))}}};F=s.Node.prototype.contains||function(a){return!!(this.compareDocumentPosition(a)&16)};var L=/[\uD800-\uDBFF][\uDC00-\uDFFF]/g,M=/([^#-~ |!])/g,d=k("area,br,col,hr,img,wbr"),y=k("colgroup,dd,dt,li,p,tbody,td,tfoot,th,thead,tr"),m=k("rp,rt"),r=l({},m,y),y=l({},y,k("address,article,aside,blockquote,caption,center,del,dir,div,dl,figure,figcaption,footer,h1,h2,h3,h4,h5,h6,header,hgroup,hr,ins,map,menu,nav,ol,pre,section,table,ul")),
m=l({},m,k("a,abbr,acronym,b,bdi,bdo,big,br,cite,code,del,dfn,em,font,i,img,ins,kbd,label,map,mark,q,ruby,rp,rt,s,samp,small,span,strike,strong,sub,sup,time,tt,u,var")),z=k("circle,defs,desc,ellipse,font-face,font-face-name,font-face-src,g,glyph,hkern,image,linearGradient,line,marker,metadata,missing-glyph,mpath,path,polygon,polyline,radialGradient,rect,stop,svg,switch,text,title,tspan"),A=k("script,style"),p=l({},d,y,m,r),n=k("background,cite,href,longdesc,src,xlink:href"),r=k("abbr,align,alt,axis,bgcolor,border,cellpadding,cellspacing,class,clear,color,cols,colspan,compact,coords,dir,face,headers,height,hreflang,hspace,ismap,lang,language,nohref,nowrap,rel,rev,rows,rowspan,rules,scope,scrolling,shape,size,span,start,summary,tabindex,target,title,type,valign,value,vspace,width"),
m=k("accent-height,accumulate,additive,alphabetic,arabic-form,ascent,baseProfile,bbox,begin,by,calcMode,cap-height,class,color,color-rendering,content,cx,cy,d,dx,dy,descent,display,dur,end,fill,fill-rule,font-family,font-size,font-stretch,font-style,font-variant,font-weight,from,fx,fy,g1,g2,glyph-name,gradientUnits,hanging,height,horiz-adv-x,horiz-origin-x,ideographic,k,keyPoints,keySplines,keyTimes,lang,marker-end,marker-mid,marker-start,markerHeight,markerUnits,markerWidth,mathematical,max,min,offset,opacity,orient,origin,overline-position,overline-thickness,panose-1,path,pathLength,points,preserveAspectRatio,r,refX,refY,repeatCount,repeatDur,requiredExtensions,requiredFeatures,restart,rotate,rx,ry,slope,stemh,stemv,stop-color,stop-opacity,strikethrough-position,strikethrough-thickness,stroke,stroke-dasharray,stroke-dashoffset,stroke-linecap,stroke-linejoin,stroke-miterlimit,stroke-opacity,stroke-width,systemLanguage,target,text-anchor,to,transform,type,u1,u2,underline-position,underline-thickness,unicode,unicode-range,units-per-em,values,version,viewBox,visibility,width,widths,x,x-height,x1,x2,xlink:actuate,xlink:arcrole,xlink:role,xlink:show,xlink:title,xlink:type,xml:base,xml:lang,xml:space,xmlns,xmlns:xlink,y,y1,y2,zoomAndPan",
!0),u=l({},n,m,r),g=function(a){if(a.document&&a.document.implementation)a=a.document.implementation.createHTMLDocument("inert");else throw w("noinert");return(a.documentElement||a.getDocumentElement()).getElementsByTagName("body")[0]}(s)}).info({angularVersion:"1.6.5-build.5390+sha.a86a319"});e.module("ngSanitize").filter("linky",["$sanitize",function(k){var l=/((ftp|https?):\/\/|(www\.)|(mailto:)?[A-Za-z0-9._%+-]+@)\S*[^\s.;,(){}<>"\u201d\u2019]/i,q=/^mailto:/i,s=e.$$minErr("linky"),t=e.isDefined,
x=e.isFunction,v=e.isObject,w=e.isString;return function(d,e,m){function r(a){a&&n.push(J(a))}function z(a,d){var b,c=A(a);n.push("<a ");for(b in c)n.push(b+'="'+c[b]+'" ');!t(e)||"target"in c||n.push('target="',e,'" ');n.push('href="',a.replace(/"/g,"&quot;"),'">');r(d);n.push("</a>")}if(null==d||""===d)return d;if(!w(d))throw s("notstring",d);for(var A=x(m)?m:v(m)?function(){return m}:function(){return{}},p=d,n=[],u,g;d=p.match(l);)u=d[0],d[2]||d[4]||(u=(d[3]?"http://":"mailto:")+u),g=d.index,r(p.substr(0,
g)),z(u,d[0].replace(q,"")),p=p.substring(g+d[0].length);r(p);return k(n.join(""))}}])})(window,window.angular);
//# sourceMappingURL=angular-sanitize.min.js.map