大数据脱敏性能测试
---

# 1. 测试说明
## 1.1 实验目的
得到DataX-Masking在不同数据规模、不同数据集上，几种脱敏方法的性能数据；不同参数设置下的性能表现。

## 1.2	环境配置
* 内存	64GB
* CPU	Intel(R) Core(TM) i7-6850K CPU @ 3.60GHz 12核
* 操作系统	Ubuntu 16.04.2 LTS 
* 硬盘	3TB ST3000DM001-1ER1

## 1.3	测试数据
使用多个txt文件作为数据源，数据量设置为1千行，1万行，10万行，100万行，1千万行，1亿行，10亿行。每行三列数据，类型分别为int，string，float，逗号分隔，参考[数据样例](samples.txt)。

## 1.4 脱敏方法说明
目前测试覆盖了5种常用的脱敏方法：

|脱敏方法名称|描述|示例|
|---|---|---|
|Hiding|将数据置为常量，一般用于处理不需要的敏感字段。|500 ->0<br>false->true|
|Floor|对整数或浮点数或者日期向下取整。|-12.68->-12<br>12580->12000<br>2018-05-10 10:17->2018-05-01 6:00|
|Enumerate|将数字映射为新值，同时保持数据的大小顺序。|500->1500 600->1860 700->2000|
|Prefix Preserve|保持前n位不变，混淆其余部分。可针对字母和数字字符在同为字母或数字范围内进行混淆，特殊符号将保留。|10.199.90.105->10.199.38.154<br>18965432100->18985214789|
|MD5|不可逆的hash摘要方法。将不定长的数据映射成定长的数据(长度为32的字符串)。|你好世界！->4f025928d787aa7b73beb58c1a85b11d|

# 2. 测试结果

## 2.1 声明
* 数据来源为DataX执行任务后输出的log报告。
* 测试中的主要变量包括：数据规模、并发控制（channel数），并尽可能保证其他变量一致。
## 2.2 数据走势
随着数据规模的增大，作业耗费时间基本随之线性增长。提升channel数虽然能提高效率，但channel数量和性能提高程度并不是线性关系——提高并发要结合实际情况。

channel 1
![channel 1](img/channel1.PNG)

channel 5
![channel 5](img/channel5.PNG)

channel 10
![channel 10](img/channel10.PNG)

处理10亿规模数据，改变channel对脱敏作业时间耗费的影响
![channels](img/channels.PNG)

## 2.3 结果数据
<table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;mso-yfti-tbllook:1184;mso-padding-alt:
 0cm 5.4pt 0cm 5.4pt">
 <tbody><tr style="mso-yfti-irow:0;mso-yfti-firstrow:yes;height:14.25pt">
  <td width="100%" nowrap="" colspan="3" valign="bottom" style="width:100.0%;
  border:solid windowtext 1.0pt;mso-border-alt:solid windowtext .5pt;
  ;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">时间解释说明<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:1;height:14.25pt">
  <td width="15%" nowrap="" valign="bottom" style="width:15.2%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;background:
  #DDEBF7;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="left" style="text-align:left;mso-pagination:widow-orphan"><b><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">时间类型<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="55%" nowrap="" valign="bottom" style="width:55.92%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  ;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="left" style="text-align:left;mso-pagination:widow-orphan"><b><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">解释说明<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="28%" nowrap="" valign="bottom" style="width:28.88%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  ;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="left" style="text-align:left;mso-pagination:widow-orphan"><b><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">补充<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:2;height:14.25pt">
  <td width="15%" nowrap="" style="width:15.2%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;background:
  #DDEBF7;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="left" style="text-align:left;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">总时间<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
  <td width="55%" nowrap="" valign="bottom" style="width:55.92%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  ;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="left" style="text-align:left;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">处理全部数据的时间 ≈（读时间<span lang="EN-US">+transformer</span>时间<span lang="EN-US">+</span>写时间）<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
  <td width="28%" nowrap="" valign="bottom" style="width:28.88%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  ;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="left" style="text-align:left;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">最小耗时<span lang="EN-US"> = 10s<o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:3;height:14.25pt">
  <td width="15%" nowrap="" style="width:15.2%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;background:
  #DDEBF7;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="left" style="text-align:left;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">transformer</span><span style="font-size:11.0pt;mso-ascii-font-family:
  等线;mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">时间<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
  <td width="55%" nowrap="" style="width:55.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  ;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="left" style="text-align:left;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">进行数据转换（脱敏）的总计耗时<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
  <td width="28%" rowspan="3" valign="top" style="width:28.88%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  ;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="left" style="text-align:left;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">多<span lang="EN-US">channel</span>情况<span lang="EN-US">channel</span>之间并行执行<span lang="EN-US"><br>
  </span>总计耗时为每个<span lang="EN-US">channel</span>耗时之和<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:4;height:14.25pt">
  <td width="15%" nowrap="" valign="bottom" style="width:15.2%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;background:
  #DDEBF7;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="left" style="text-align:left;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">reader</span><span style="font-size:11.0pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">时间<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
  <td width="55%" nowrap="" valign="bottom" style="width:55.92%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  ;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="left" style="text-align:left;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">进行数据读取的总计耗时<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:5;mso-yfti-lastrow:yes;height:14.25pt">
  <td width="15%" nowrap="" valign="bottom" style="width:15.2%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;background:
  #DDEBF7;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="left" style="text-align:left;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">writer</span><span style="font-size:11.0pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">时间<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
  <td width="55%" nowrap="" valign="bottom" style="width:55.92%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  ;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="left" style="text-align:left;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">进行数据写入的总计耗时<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
 </tr>
</tbody></table>

### channel = 1
<table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;mso-yfti-tbllook:1184;mso-padding-alt:
 0cm 5.4pt 0cm 5.4pt">
 <tbody><tr style="mso-yfti-irow:0;mso-yfti-firstrow:yes;height:14.25pt">
  <td width="26%" nowrap="" rowspan="2" style="width:26.74%;border:solid windowtext 1.0pt;
  mso-border-top-alt:1.0pt;mso-border-left-alt:1.0pt;mso-border-bottom-alt:
  .5pt;mso-border-right-alt:.5pt;mso-border-color-alt:windowtext;mso-border-style-alt:
  solid;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">　<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
  <td width="73%" colspan="7" style="width:73.26%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid black 1.0pt;background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">enum<o:p></o:p></span></b></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:1;height:14.25pt">
  <td width="73%" colspan="7" style="width:73.26%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">（将数字映射为新值，同时保持数据的大小顺序）<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:2;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">数据量（条）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10000<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1M<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10M<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100M<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1G<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:3;height:14.25pt">
  <td width="26%" nowrap="" style="width:26.74%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:
  0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">总时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">20<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">70<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">670<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:4;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">transformer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">脱敏时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.056<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.319<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">4.252<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.837<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">13.503<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">81.333<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">774.464<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:5;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">reader</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.836<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.202<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10.288<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">8.969<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">55.359<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">324.091<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">2653.187<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:6;height:15.0pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">writer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.003<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.023<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.091<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.113<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">2.825<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">8.402<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">170.401<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:7;height:15.0pt">
  <td width="26%" nowrap="" valign="bottom" style="width:26.74%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="8%" nowrap="" valign="bottom" style="width:8.18%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="10%" nowrap="" valign="bottom" style="width:10.94%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="11%" nowrap="" valign="bottom" style="width:11.66%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
 </tr>
 <tr style="mso-yfti-irow:8;height:14.25pt">
  <td width="26%" nowrap="" rowspan="2" style="width:26.74%;border:solid windowtext 1.0pt;
  mso-border-top-alt:1.0pt;mso-border-left-alt:1.0pt;mso-border-bottom-alt:
  .5pt;mso-border-right-alt:.5pt;mso-border-color-alt:windowtext;mso-border-style-alt:
  solid;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">　<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
  <td width="73%" colspan="7" style="width:73.26%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid black 1.0pt;background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">floor<o:p></o:p></span></b></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:9;height:14.25pt">
  <td width="73%" colspan="7" style="width:73.26%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">（对整数或浮点数或者日期向下取整）<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:10;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">数据量（条）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10000<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1M<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10M<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100M<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1G<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:11;height:14.25pt">
  <td width="26%" nowrap="" style="width:26.74%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:
  0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">总时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">60<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">580<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:12;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">transformer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">脱敏时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.04<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.23<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.408<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.812<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">4.405<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">36.639<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">310.808<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:13;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">reader</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.329<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.755<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.761<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">6.775<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">34.49<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">289.341<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1596.446<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:14;height:15.0pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">writer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.004<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.019<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.036<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.107<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.915<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">7.932<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">57.915<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:15;height:15.0pt">
  <td width="26%" nowrap="" valign="bottom" style="width:26.74%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="8%" nowrap="" valign="bottom" style="width:8.18%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="10%" nowrap="" valign="bottom" style="width:10.94%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="11%" nowrap="" valign="bottom" style="width:11.66%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
 </tr>
 <tr style="mso-yfti-irow:16;height:14.25pt">
  <td width="26%" nowrap="" rowspan="2" style="width:26.74%;border:solid windowtext 1.0pt;
  mso-border-top-alt:1.0pt;mso-border-left-alt:1.0pt;mso-border-bottom-alt:
  .5pt;mso-border-right-alt:.5pt;mso-border-color-alt:windowtext;mso-border-style-alt:
  solid;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">　<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
  <td width="73%" colspan="7" style="width:73.26%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid black 1.0pt;background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">hiding<o:p></o:p></span></b></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:17;height:14.25pt">
  <td width="73%" colspan="7" style="width:73.26%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">（将数据置为常量，一般用于处理不需要的敏感字段）<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:18;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">数据量（条）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10000<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1M<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10M<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100M<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1G<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:19;height:14.25pt">
  <td width="26%" nowrap="" style="width:26.74%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:
  0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">总时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">40<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">420<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:20;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">transformer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">脱敏时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.01<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.043<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.138<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.265<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.325<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">11.985<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">86.237<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:21;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">reader</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.621<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.457<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">2.125<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">7.783<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">18.264<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">153.692<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1818.202<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:22;height:15.0pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">writer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.012<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.017<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.056<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.292<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.689<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">6.737<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">49.119<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:23;height:15.0pt">
  <td width="26%" nowrap="" valign="bottom" style="width:26.74%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="8%" nowrap="" valign="bottom" style="width:8.18%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="10%" nowrap="" valign="bottom" style="width:10.94%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="11%" nowrap="" valign="bottom" style="width:11.66%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
 </tr>
 <tr style="mso-yfti-irow:24;height:14.25pt">
  <td width="26%" nowrap="" rowspan="2" style="width:26.74%;border:solid windowtext 1.0pt;
  mso-border-top-alt:1.0pt;mso-border-left-alt:1.0pt;mso-border-bottom-alt:
  .5pt;mso-border-right-alt:.5pt;mso-border-color-alt:windowtext;mso-border-style-alt:
  solid;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">　<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
  <td width="73%" colspan="7" style="width:73.26%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid black 1.0pt;background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">prefix<o:p></o:p></span></b></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:25;height:14.25pt">
  <td width="73%" colspan="7" style="width:73.26%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">（保持前<span lang="EN-US">n</span>位不变，混淆其余部分）<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:26;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">数据量（条）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10000<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1M<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10M<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100M<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1G<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:27;height:14.25pt">
  <td width="26%" nowrap="" style="width:26.74%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:
  0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">总时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">60<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">490<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:28;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">transformer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">脱敏时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.133<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.205<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.416<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.294<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">7.666<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">73.972<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">661.793<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:29;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">reader</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.641<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.818<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.985<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">8.544<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">32.279<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">274.771<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1831.984<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:30;height:15.0pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">writer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.003<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.025<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.066<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.146<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.946<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">16.866<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">50.805<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:31;height:15.0pt">
  <td width="26%" nowrap="" valign="bottom" style="width:26.74%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="8%" nowrap="" valign="bottom" style="width:8.18%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="10%" nowrap="" valign="bottom" style="width:10.94%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="11%" nowrap="" valign="bottom" style="width:11.66%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
 </tr>
 <tr style="mso-yfti-irow:32;height:14.25pt">
  <td width="26%" nowrap="" rowspan="2" style="width:26.74%;border:solid windowtext 1.0pt;
  mso-border-top-alt:1.0pt;mso-border-left-alt:1.0pt;mso-border-bottom-alt:
  .5pt;mso-border-right-alt:.5pt;mso-border-color-alt:windowtext;mso-border-style-alt:
  solid;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">　<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
  <td width="73%" colspan="7" style="width:73.26%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid black 1.0pt;background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">MD5<o:p></o:p></span></b></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:33;height:14.25pt">
  <td width="73%" colspan="7" style="width:73.26%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">(</span><span style="font-size:11.0pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">不可逆的<span lang="EN-US">hash</span>摘要方法<span lang="EN-US">)<o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:34;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">数据量（条）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10000<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1M<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10M<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100M<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1G<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:35;height:14.25pt">
  <td width="26%" nowrap="" style="width:26.74%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:
  0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">总时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">20<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">150<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="right" style="text-align:right;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">880<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:36;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">transformer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">脱敏时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.43<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.934<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.547<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">5.625<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">42.514<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">392.165<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="right" style="text-align:right;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">2136.568<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:37;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">reader</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.536<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">2.176<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">4.485<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">13.973<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">80.309<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">711.103<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="right" style="text-align:right;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">4014.058<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:38;mso-yfti-lastrow:yes;height:15.0pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">writer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.003<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.042<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.047<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.292<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">4.837<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">71.013<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="right" style="text-align:right;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">80.022<o:p></o:p></span></p>
  </td>
 </tr>
</tbody></table>

### channel = 5
<table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;mso-yfti-tbllook:1184;mso-padding-alt:
 0cm 5.4pt 0cm 5.4pt">
 <tbody><tr style="mso-yfti-irow:0;mso-yfti-firstrow:yes;height:14.25pt">
  <td width="26%" nowrap="" rowspan="2" style="width:26.74%;border:solid windowtext 1.0pt;
  mso-border-top-alt:1.0pt;mso-border-left-alt:1.0pt;mso-border-bottom-alt:
  .5pt;mso-border-right-alt:.5pt;mso-border-color-alt:windowtext;mso-border-style-alt:
  solid;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt"></td>
  <td width="73%" colspan="7" style="width:73.26%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid black 1.0pt;background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">enum<o:p></o:p></span></b></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:1;height:14.25pt">
  <td width="73%" colspan="7" style="width:73.26%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">（将数字映射为新值，同时保持数据的大小顺序）<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:2;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">数据量（条）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10000<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1M<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10M<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100M<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1G<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:3;height:14.25pt">
  <td width="26%" nowrap="" style="width:26.74%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:
  0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">总时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">20<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">70<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">670<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:4;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">transformer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">脱敏时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.056<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.319<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">4.252<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.837<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">13.503<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">81.333<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">774.464<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:5;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">reader</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.836<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.202<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10.288<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">8.969<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">55.359<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">324.091<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">2653.187<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:6;height:15.0pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">writer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.003<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.023<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.091<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.113<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">2.825<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">8.402<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">170.401<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:7;height:15.0pt">
  <td width="26%" nowrap="" valign="bottom" style="width:26.74%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="8%" nowrap="" valign="bottom" style="width:8.18%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="10%" nowrap="" valign="bottom" style="width:10.94%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="11%" nowrap="" valign="bottom" style="width:11.66%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
 </tr>
 <tr style="mso-yfti-irow:8;height:14.25pt">
  <td width="26%" nowrap="" rowspan="2" style="width:26.74%;border:solid windowtext 1.0pt;
  mso-border-top-alt:1.0pt;mso-border-left-alt:1.0pt;mso-border-bottom-alt:
  .5pt;mso-border-right-alt:.5pt;mso-border-color-alt:windowtext;mso-border-style-alt:
  solid;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt"></td>
  <td width="73%" colspan="7" style="width:73.26%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid black 1.0pt;background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">floor<o:p></o:p></span></b></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:9;height:14.25pt">
  <td width="73%" colspan="7" style="width:73.26%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">（对整数或浮点数或者日期向下取整）<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:10;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">数据量（条）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10000<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1M<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10M<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100M<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1G<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:11;height:14.25pt">
  <td width="26%" nowrap="" style="width:26.74%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:
  0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">总时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">60<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">580<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:12;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">transformer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">脱敏时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.04<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.23<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.408<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.812<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">4.405<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">36.639<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">310.808<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:13;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">reader</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.329<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.755<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.761<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">6.775<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">34.49<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">289.341<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1596.446<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:14;height:15.0pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">writer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.004<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.019<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.036<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.107<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.915<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">7.932<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">57.915<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:15;height:15.0pt">
  <td width="26%" nowrap="" valign="bottom" style="width:26.74%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="8%" nowrap="" valign="bottom" style="width:8.18%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="10%" nowrap="" valign="bottom" style="width:10.94%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="11%" nowrap="" valign="bottom" style="width:11.66%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
 </tr>
 <tr style="mso-yfti-irow:16;height:14.25pt">
  <td width="26%" nowrap="" rowspan="2" style="width:26.74%;border:solid windowtext 1.0pt;
  mso-border-top-alt:1.0pt;mso-border-left-alt:1.0pt;mso-border-bottom-alt:
  .5pt;mso-border-right-alt:.5pt;mso-border-color-alt:windowtext;mso-border-style-alt:
  solid;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt"></td>
  <td width="73%" colspan="7" style="width:73.26%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid black 1.0pt;background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">hiding<o:p></o:p></span></b></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:17;height:14.25pt">
  <td width="73%" colspan="7" style="width:73.26%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">（将数据置为常量，一般用于处理不需要的敏感字段）<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:18;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">数据量（条）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10000<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1M<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10M<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100M<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1G<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:19;height:14.25pt">
  <td width="26%" nowrap="" style="width:26.74%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:
  0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">总时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">40<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">420<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:20;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">transformer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">脱敏时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.01<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.043<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.138<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.265<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.325<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">11.985<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">86.237<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:21;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">reader</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.621<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.457<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">2.125<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">7.783<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">18.264<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">153.692<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1818.202<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:22;height:15.0pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">writer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.012<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.017<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.056<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.292<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.689<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">6.737<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">49.119<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:23;height:15.0pt">
  <td width="26%" nowrap="" valign="bottom" style="width:26.74%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="8%" nowrap="" valign="bottom" style="width:8.18%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="10%" nowrap="" valign="bottom" style="width:10.94%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="11%" nowrap="" valign="bottom" style="width:11.66%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
 </tr>
 <tr style="mso-yfti-irow:24;height:14.25pt">
  <td width="26%" nowrap="" rowspan="2" style="width:26.74%;border:solid windowtext 1.0pt;
  mso-border-top-alt:1.0pt;mso-border-left-alt:1.0pt;mso-border-bottom-alt:
  .5pt;mso-border-right-alt:.5pt;mso-border-color-alt:windowtext;mso-border-style-alt:
  solid;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt"></td>
  <td width="73%" colspan="7" style="width:73.26%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid black 1.0pt;background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">prefix<o:p></o:p></span></b></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:25;height:14.25pt">
  <td width="73%" colspan="7" style="width:73.26%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">（保持前<span lang="EN-US">n</span>位不变，混淆其余部分）<span lang="EN-US"><o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:26;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">数据量（条）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10000<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1M<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10M<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100M<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1G<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:27;height:14.25pt">
  <td width="26%" nowrap="" style="width:26.74%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:
  0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">总时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">60<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">490<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:28;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">transformer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">脱敏时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.133<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.205<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.416<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.294<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">7.666<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">73.972<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">661.793<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:29;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">reader</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.641<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.818<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.985<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">8.544<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">32.279<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">274.771<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1831.984<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:30;height:15.0pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">writer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.003<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.025<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.066<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.146<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.946<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">16.866<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">50.805<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:31;height:15.0pt">
  <td width="26%" nowrap="" valign="bottom" style="width:26.74%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="8%" nowrap="" valign="bottom" style="width:8.18%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="10%" nowrap="" valign="bottom" style="width:10.94%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.92%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="11%" nowrap="" valign="bottom" style="width:11.66%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
 </tr>
 <tr style="mso-yfti-irow:32;height:14.25pt">
  <td width="26%" nowrap="" rowspan="2" style="width:26.74%;border:solid windowtext 1.0pt;
  mso-border-top-alt:1.0pt;mso-border-left-alt:1.0pt;mso-border-bottom-alt:
  .5pt;mso-border-right-alt:.5pt;mso-border-color-alt:windowtext;mso-border-style-alt:
  solid;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt"></td>
  <td width="73%" colspan="7" style="width:73.26%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid black 1.0pt;background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">MD5<o:p></o:p></span></b></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:33;height:14.25pt">
  <td width="73%" colspan="7" style="width:73.26%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">(</span><span style="font-size:11.0pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">不可逆的<span lang="EN-US">hash</span>摘要方法<span lang="EN-US">)<o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:34;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">数据量（条）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10000<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100000<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1M<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10M<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">100M<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" style="width:13.42%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1G<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:35;height:14.25pt">
  <td width="26%" nowrap="" style="width:26.74%;border:solid windowtext 1.0pt;
  border-top:none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:
  0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">总时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">10<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">20<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">150<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">880<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:36;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">transformer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">脱敏时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.43<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.934<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.547<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">5.625<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">42.514<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">392.165<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">2136.568<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:37;height:14.25pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">reader</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">1.536<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">2.176<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">4.485<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">13.973<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">80.309<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">711.103<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">4014.058<o:p></o:p></span></p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:38;mso-yfti-lastrow:yes;height:15.0pt">
  <td width="26%" style="width:26.74%;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext 1.0pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><b><span lang="EN-US" style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;
  mso-fareast-font-family:等线;mso-hansi-font-family:等线;mso-bidi-font-family:
  宋体;color:black;mso-font-kerning:0pt">writer</span></b><b><span style="mso-bidi-font-size:10.5pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">等待时间（<span lang="EN-US">s</span>）<span lang="EN-US"><o:p></o:p></span></span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.18%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.003<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.042<o:p></o:p></span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.94%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.047<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">0.292<o:p></o:p></span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.92%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">4.837<o:p></o:p></span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.66%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">71.013<o:p></o:p></span></p>
  </td>
  <td width="13%" nowrap="" valign="bottom" style="width:13.42%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center;mso-pagination:widow-orphan"><span lang="EN-US" style="font-size:11.0pt;mso-ascii-font-family:等线;mso-fareast-font-family:
  等线;mso-hansi-font-family:等线;mso-bidi-font-family:宋体;color:black;mso-font-kerning:
  0pt">80.022<o:p></o:p></span></p>
  </td>
 </tr>
</tbody></table>

### channel = 10
<table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse">
 <tbody><tr style="height:14.25pt">
  <td width="21%" nowrap="" rowspan="2" style="width:21.54%;border:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span style="font-size:11.0pt;color:black">　</span></p>
  </td>
  <td width="78%" colspan="7" style="width:78.46%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="font-size:11.0pt;color:black">enum</span></b></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="78%" colspan="7" style="width:78.46%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span style="font-size:11.0pt;color:black">（将数字映射为新值，同时保持数据的大小顺序）</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span style="color:black">数据量（条）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1000</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10000</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">100000</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1M</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10M</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">100M</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1G</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" nowrap="" style="width:21.54%;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span style="font-size:11.0pt;color:black">总时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">20</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">90</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">470</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">transformer</span></b><b><span style="color:black">脱敏时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.082</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.433</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.91</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">2.482</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">12.185</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">70.939</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">851.886</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">reader</span></b><b><span style="color:black">等待时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.538</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1.238</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">2.865</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">12.387</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">102.359</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">834.607</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">4237.98</span></p>
  </td>
 </tr>
 <tr style="height:15.0pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">writer</span></b><b><span style="color:black">等待时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.004</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.072</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.103</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.139</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.607</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">5.294</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">76.167</span></p>
  </td>
 </tr>
 <tr style="height:15.0pt">
  <td width="21%" nowrap="" valign="bottom" style="width:21.54%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="8%" nowrap="" valign="bottom" style="width:8.54%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.62%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="11%" nowrap="" valign="bottom" style="width:11.46%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="10%" nowrap="" valign="bottom" style="width:10.38%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="12%" nowrap="" valign="bottom" style="width:12.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="12%" nowrap="" valign="bottom" style="width:12.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="14%" nowrap="" valign="bottom" style="width:14.04%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" nowrap="" rowspan="2" style="width:21.54%;border:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span style="font-size:11.0pt;color:black">　</span></p>
  </td>
  <td width="78%" colspan="7" style="width:78.46%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="font-size:11.0pt;color:black">floor</span></b></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="78%" colspan="7" style="width:78.46%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span style="font-size:11.0pt;color:black">（对整数或浮点数或者日期向下取整）</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span style="color:black">数据量（条）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1000</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10000</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">100000</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1M</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10M</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">100M</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1G</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" nowrap="" style="width:21.54%;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span style="font-size:11.0pt;color:black">总时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">80</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">360</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">transformer</span></b><b><span style="color:black">脱敏时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.025</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.18</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1.227</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">4.1</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">5.977</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">31.827</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">352.033</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">reader</span></b><b><span style="color:black">等待时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">2.519</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1.113</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">5.388</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">20.118</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">89.618</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">707.14</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">2692.326</span></p>
  </td>
 </tr>
 <tr style="height:15.0pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">writer</span></b><b><span style="color:black">等待时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.003</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.027</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.095</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.157</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.692</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">5.044</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">70.625</span></p>
  </td>
 </tr>
 <tr style="height:15.0pt">
  <td width="21%" nowrap="" valign="bottom" style="width:21.54%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="8%" nowrap="" valign="bottom" style="width:8.54%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.62%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="11%" nowrap="" valign="bottom" style="width:11.46%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="10%" nowrap="" valign="bottom" style="width:10.38%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="12%" nowrap="" valign="bottom" style="width:12.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="12%" nowrap="" valign="bottom" style="width:12.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="14%" nowrap="" valign="bottom" style="width:14.04%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" nowrap="" rowspan="2" style="width:21.54%;border:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span style="font-size:11.0pt;color:black">　</span></p>
  </td>
  <td width="78%" colspan="7" style="width:78.46%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="font-size:11.0pt;color:black">hiding</span></b></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="78%" colspan="7" style="width:78.46%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span style="font-size:11.0pt;color:black">（将数据置为常量，一般用于处理不需要的敏感字段）</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span style="color:black">数据量（条）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1000</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10000</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">100000</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1M</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10M</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">100M</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1G</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" nowrap="" style="width:21.54%;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span style="font-size:11.0pt;color:black">总时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">20</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">70</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">450</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">transformer</span></b><b><span style="color:black">脱敏时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.022</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.071</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.188</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.521</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1.344</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">8.127</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">122.608</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">reader</span></b><b><span style="color:black">等待时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.595</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1.035</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">3.409</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">15.856</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">92.135</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">619.548</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">4175.852</span></p>
  </td>
 </tr>
 <tr style="height:15.0pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">writer</span></b><b><span style="color:black">等待时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.004</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.028</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.192</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.124</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.646</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">4.995</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">72.52</span></p>
  </td>
 </tr>
 <tr style="height:15.0pt">
  <td width="21%" nowrap="" valign="bottom" style="width:21.54%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="8%" nowrap="" valign="bottom" style="width:8.54%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.62%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="11%" nowrap="" valign="bottom" style="width:11.46%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="10%" nowrap="" valign="bottom" style="width:10.38%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="12%" nowrap="" valign="bottom" style="width:12.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="12%" nowrap="" valign="bottom" style="width:12.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="14%" nowrap="" valign="bottom" style="width:14.04%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" nowrap="" rowspan="2" style="width:21.54%;border:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span style="font-size:11.0pt;color:black">　</span></p>
  </td>
  <td width="78%" colspan="7" style="width:78.46%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="font-size:11.0pt;color:black">prefix</span></b></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="78%" colspan="7" style="width:78.46%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span style="font-size:11.0pt;color:black">（保持前<span lang="EN-US">n</span>位不变，混淆其余部分）</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span style="color:black">数据量（条）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1000</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10000</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">100000</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1M</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10M</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">100M</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1G</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" nowrap="" style="width:21.54%;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span style="font-size:11.0pt;color:black">总时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">20</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">40</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">380</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">transformer</span></b><b><span style="color:black">脱敏时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.208</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.388</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.564</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1.991</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">7.744</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">130.437</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1350.111</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">reader</span></b><b><span style="color:black">等待时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.63</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1.317</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">3.686</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">14.955</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">94.56</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">307.554</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">3454.524</span></p>
  </td>
 </tr>
 <tr style="height:15.0pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">writer</span></b><b><span style="color:black">等待时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.01</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.036</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.084</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.244</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.784</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10.775</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">106.415</span></p>
  </td>
 </tr>
 <tr style="height:15.0pt">
  <td width="21%" nowrap="" valign="bottom" style="width:21.54%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="8%" nowrap="" valign="bottom" style="width:8.54%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="9%" nowrap="" valign="bottom" style="width:9.62%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="11%" nowrap="" valign="bottom" style="width:11.46%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="10%" nowrap="" valign="bottom" style="width:10.38%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="12%" nowrap="" valign="bottom" style="width:12.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="12%" nowrap="" valign="bottom" style="width:12.2%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
  <td width="14%" nowrap="" valign="bottom" style="width:14.04%;padding:0cm 5.4pt 0cm 5.4pt;
  height:15.0pt"></td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" nowrap="" rowspan="2" style="width:21.54%;border:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span style="font-size:11.0pt;color:black">　</span></p>
  </td>
  <td width="78%" colspan="7" style="width:78.46%;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  background:#70AD47;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="font-size:11.0pt;color:black">MD5</span></b></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="78%" colspan="7" style="width:78.46%;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">(</span><span style="font-size:11.0pt;
  color:black">不可逆的<span lang="EN-US">hash</span>摘要方法<span lang="EN-US">)</span></span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span style="color:black">数据量（条）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1000</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10000</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">100000</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1M</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10M</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">100M</span></p>
  </td>
  <td width="14%" nowrap="" style="width:14.04%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1G</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" nowrap="" style="width:21.54%;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span style="font-size:11.0pt;color:black">总时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">20</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">90</span></p>
  </td>
  <td width="14%" nowrap="" valign="bottom" style="width:14.04%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="right" style="text-align:right"><span lang="EN-US" style="font-size:11.0pt;color:black">630</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">transformer</span></b><b><span style="color:black">脱敏时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1.618</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.902</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">2.096</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">12.549</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">34.589</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">230.964</span></p>
  </td>
  <td width="14%" nowrap="" valign="bottom" style="width:14.04%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="right" style="text-align:right"><span lang="EN-US" style="font-size:11.0pt;color:black">3215.785</span></p>
  </td>
 </tr>
 <tr style="height:14.25pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">reader</span></b><b><span style="color:black">等待时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">2.222</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">2.076</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">4.445</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">27.293</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">105.275</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">850.588</span></p>
  </td>
  <td width="14%" nowrap="" valign="bottom" style="width:14.04%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:14.25pt">
  <p class="MsoNormal" align="right" style="text-align:right"><span lang="EN-US" style="font-size:11.0pt;color:black">6051.006</span></p>
  </td>
 </tr>
 <tr style="height:15.0pt">
  <td width="21%" style="width:21.54%;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><b><span lang="EN-US" style="color:black">writer</span></b><b><span style="color:black">等待时间（<span lang="EN-US">s</span>）</span></b></p>
  </td>
  <td width="8%" nowrap="" style="width:8.54%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.005</span></p>
  </td>
  <td width="9%" nowrap="" style="width:9.62%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.039</span></p>
  </td>
  <td width="11%" nowrap="" style="width:11.46%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.051</span></p>
  </td>
  <td width="10%" nowrap="" style="width:10.38%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">0.243</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">1.112</span></p>
  </td>
  <td width="12%" nowrap="" style="width:12.2%;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="center" style="text-align:center"><span lang="EN-US" style="font-size:11.0pt;color:black">10.803</span></p>
  </td>
  <td width="14%" nowrap="" valign="bottom" style="width:14.04%;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:15.0pt">
  <p class="MsoNormal" align="right" style="text-align:right"><span lang="EN-US" style="font-size:11.0pt;color:black">200.931</span></p>
  </td>
 </tr>
</tbody></table>

