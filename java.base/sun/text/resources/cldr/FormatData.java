/*
 * Copyright (c) 2012, 2020, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * COPYRIGHT AND PERMISSION NOTICE
 *
 * Copyright (C) 1991-2016 Unicode, Inc. All rights reserved.
 * Distributed under the Terms of Use in 
 * http://www.unicode.org/copyright.html.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of the Unicode data files and any associated documentation
 * (the "Data Files") or Unicode software and any associated documentation
 * (the "Software") to deal in the Data Files or Software
 * without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, and/or sell copies of
 * the Data Files or Software, and to permit persons to whom the Data Files
 * or Software are furnished to do so, provided that
 * (a) this copyright and permission notice appear with all copies 
 * of the Data Files or Software,
 * (b) this copyright and permission notice appear in associated 
 * documentation, and
 * (c) there is clear notice in each modified Data File or in the Software
 * as well as in the documentation associated with the Data File(s) or
 * Software that the data or software has been modified.
 *
 * THE DATA FILES AND SOFTWARE ARE PROVIDED "AS IS", WITHOUT WARRANTY OF
 * ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT OF THIRD PARTY RIGHTS.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR HOLDERS INCLUDED IN THIS
 * NOTICE BE LIABLE FOR ANY CLAIM, OR ANY SPECIAL INDIRECT OR CONSEQUENTIAL
 * DAMAGES, OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE,
 * DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER
 * TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THE DATA FILES OR SOFTWARE.
 *
 * Except as contained in this notice, the name of a copyright holder
 * shall not be used in advertising or otherwise to promote the sale,
 * use or other dealings in these Data Files or Software without prior
 * written authorization of the copyright holder.
 */

package sun.text.resources.cldr;

import java.util.ListResourceBundle;

public class FormatData extends ListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] metaValue_MonthNames = new String[] {
               "Jan",
               "Feb",
               "Mar",
               "Apr",
               "May",
               "Jun",
               "Jul",
               "Aug",
               "Sep",
               "Oct",
               "Nov",
               "Dec",
               "",
            };
        final String[] metaValue_MonthNarrows = new String[] {
               "1",
               "2",
               "3",
               "4",
               "5",
               "6",
               "7",
               "8",
               "9",
               "10",
               "11",
               "12",
               "",
            };
        final String[] metaValue_DayNames = new String[] {
               "Sun",
               "Mon",
               "Tue",
               "Wed",
               "Thu",
               "Fri",
               "Sat",
            };
        final String[] metaValue_DayNarrows = new String[] {
               "S",
               "M",
               "T",
               "W",
               "T",
               "F",
               "S",
            };
        final String[] metaValue_QuarterNames = new String[] {
               "Q1",
               "Q2",
               "Q3",
               "Q4",
            };
        final String[] metaValue_QuarterNarrows = new String[] {
               "1",
               "2",
               "3",
               "4",
            };
        final String[] metaValue_AmPmMarkers = new String[] {
               "AM",
               "PM",
            };
        final String[] metaValue_long_Eras = new String[] {
               "BCE",
               "CE",
            };
        final String[] metaValue_TimePatterns = new String[] {
               "HH:mm:ss zzzz",
               "HH:mm:ss z",
               "HH:mm:ss",
               "HH:mm",
            };
        final String[] metaValue_java_time_buddhist_long_Eras = new String[] {
               "BC",
               "BE",
            };
        final String[] metaValue_java_time_buddhist_DatePatterns = new String[] {
               "G y MMMM d, EEEE",
               "G y MMMM d",
               "G y MMM d",
               "GGGGG y-MM-dd",
            };
        final String[] metaValue_buddhist_DatePatterns = new String[] {
               "GGGG y MMMM d, EEEE",
               "GGGG y MMMM d",
               "GGGG y MMM d",
               "G y-MM-dd",
            };
        final String[] metaValue_java_time_japanese_long_Eras = new String[] {
               "CE",
               "Meiji",
               "Taish\u014d",
               "Sh\u014dwa",
               "Heisei",
               "Reiwa",
            };
        final String[] metaValue_java_time_japanese_narrow_Eras = new String[] {
               "CE",
               "M",
               "T",
               "S",
               "H",
               "R",
            };
        final String[] metaValue_java_time_roc_long_Eras = new String[] {
               "Before R.O.C.",
               "R.O.C.",
            };
        final String[] metaValue_java_time_islamic_long_Eras = new String[] {
               "",
               "AH",
            };
        final String[] metaValue_adlm_NumberElements = new String[] {
               ".",
               ",",
               ";",
               "%",
               "0",
               "#",
               "-",
               "E",
               "\u2030",
               "\u221e",
               "NaN",
               "",
               "",
            };
        final String[] metaValue_adlm_NumberPatterns = new String[] {
               "#,##0.###",
               "\u00a4\u00a0#,##0.00",
               "#,##0%",
               "",
            };
        final String[] metaValue_short_CompactNumberPatterns = new String[] {
               "",
               "",
               "",
               "{other:0K}",
               "{other:00K}",
               "{other:000K}",
               "{other:0M}",
               "{other:00M}",
               "{other:000M}",
               "{other:0G}",
               "{other:00G}",
               "{other:000G}",
               "{other:0T}",
               "{other:00T}",
               "{other:000T}",
            };
        final Object[][] data = new Object[][] {
            { "MonthNames", metaValue_MonthNames },
            { "mroo.NumberElements", metaValue_adlm_NumberElements },
            { "rohg.NumberElements", metaValue_adlm_NumberElements },
            { "buddhist.narrow.Eras", metaValue_java_time_buddhist_long_Eras },
            { "osma.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "roc.QuarterNames", metaValue_QuarterNames },
            { "lana.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1a80",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "hmng.NumberElements", metaValue_adlm_NumberElements },
            { "mathdbl.NumberElements", metaValue_adlm_NumberElements },
            { "talu.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u19d0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "gonm.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "abbreviated.AmPmMarkers", metaValue_AmPmMarkers },
            { "sind.NumberElements", metaValue_adlm_NumberElements },
            { "mtei.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\uabf0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "timezone.gmtZeroFormat", "GMT" },
            { "japanese.MonthNames", metaValue_MonthNames },
            { "standalone.DayAbbreviations", metaValue_DayNames },
            { "islamic.DayNames", metaValue_DayNames },
            { "cakm.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}",
                    "{1} {0}",
                    "{1} {0}",
                    "{1} {0}",
                }
            },
            { "narrow.AmPmMarkers", metaValue_AmPmMarkers },
            { "latn.NumberElements", metaValue_adlm_NumberElements },
            { "guru.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0a66",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "field.minute", "Minute" },
            { "buddhist.AmPmMarkers", metaValue_AmPmMarkers },
            { "mathsanb.NumberElements", metaValue_adlm_NumberElements },
            { "bali.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "standalone.MonthNarrows", metaValue_MonthNarrows },
            { "cham.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\uaa50",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "islamic.MonthNames",
                new String[] {
                    "Muharram",
                    "Safar",
                    "Rabi\u02bb I",
                    "Rabi\u02bb II",
                    "Jumada I",
                    "Jumada II",
                    "Rajab",
                    "Sha\u02bbban",
                    "Ramadan",
                    "Shawwal",
                    "Dhu\u02bbl-Qi\u02bbdah",
                    "Dhu\u02bbl-Hijjah",
                    "",
                }
            },
            { "mymrtlng.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ua9f0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "adlm.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "japanese.QuarterNarrows", metaValue_QuarterNarrows },
            { "saur.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "wcho.NumberElements", metaValue_adlm_NumberElements },
            { "arab.NumberElements",
                new String[] {
                    "\u066b",
                    "\u066c",
                    "\u061b",
                    "\u066a\u061c",
                    "\u0660",
                    "#",
                    "\u061c-",
                    "\u0627\u0633",
                    "\u0609",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "beng.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "shrd.NumberElements", metaValue_adlm_NumberElements },
            { "takr.NumberElements", metaValue_adlm_NumberElements },
            { "islamic.DatePatterns", metaValue_buddhist_DatePatterns },
            { "deva.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0966",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "islamic.Eras", metaValue_java_time_islamic_long_Eras },
            { "sund.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "arabext.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "field.second", "Second" },
            { "islamic.MonthNarrows",
                new String[] {
                    "1",
                    "2",
                    "3",
                    "4",
                    "5",
                    "6",
                    "7",
                    "8",
                    "9",
                    "10",
                    "11",
                    "12",
                    "",
                }
            },
            { "vaii.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "roc.DatePatterns", metaValue_buddhist_DatePatterns },
            { "java.time.roc.long.Eras", metaValue_java_time_roc_long_Eras },
            { "buddhist.TimePatterns", metaValue_TimePatterns },
            { "brah.NumberElements", metaValue_adlm_NumberElements },
            { "standalone.MonthAbbreviations", metaValue_MonthNames },
            { "gong.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "timezone.regionFormat", "{0}" },
            { "olck.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1c50",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "tibt.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "khmr.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "java.time.buddhist.long.Eras", metaValue_java_time_buddhist_long_Eras },
            { "orya.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "mymrshan.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1090",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "buddhist.long.Eras", metaValue_java_time_buddhist_long_Eras },
            { "java.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ua9d0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "TimePatterns", metaValue_TimePatterns },
            { "hanidec.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "roc.narrow.Eras", metaValue_java_time_roc_long_Eras },
            { "laoo.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0ed0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "sund.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1bb0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "telu.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "buddhist.narrow.AmPmMarkers", metaValue_AmPmMarkers },
            { "kali.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ua900",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "roc.DayNames", metaValue_DayNames },
            { "bhks.NumberElements", metaValue_adlm_NumberElements },
            { "QuarterNarrows", metaValue_QuarterNarrows },
            { "standalone.QuarterNarrows", metaValue_QuarterNarrows },
            { "lanatham.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "japanese.MonthAbbreviations", metaValue_MonthNames },
            { "islamic.DayAbbreviations", metaValue_DayNames },
            { "islamic.long.Eras", metaValue_java_time_islamic_long_Eras },
            { "java.time.islamic.Eras", metaValue_java_time_islamic_long_Eras },
            { "java.time.buddhist.Eras", metaValue_java_time_buddhist_long_Eras },
            { "mong.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "tamldec.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "hmnp.NumberElements", metaValue_adlm_NumberElements },
            { "mymr.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "nkoo.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u07c0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "cakm.NumberElements", metaValue_adlm_NumberElements },
            { "tibt.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0f20",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "wara.NumberElements", metaValue_adlm_NumberElements },
            { "DayNames", metaValue_DayNames },
            { "buddhist.DatePatterns", metaValue_buddhist_DatePatterns },
            { "java.time.japanese.Eras", metaValue_java_time_japanese_long_Eras },
            { "roc.MonthNames", metaValue_MonthNames },
            { "sora.NumberElements", metaValue_adlm_NumberElements },
            { "gujr.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0ae6",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "limb.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1946",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "roc.DayNarrows", metaValue_DayNarrows },
            { "shrd.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "java.time.buddhist.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "mlym.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "java.time.buddhist.narrow.Eras", metaValue_java_time_buddhist_long_Eras },
            { "timezone.regionFormat.daylight", "{0} (+1)" },
            { "DatePatterns",
                new String[] {
                    "y MMMM d, EEEE",
                    "y MMMM d",
                    "y MMM d",
                    "y-MM-dd",
                }
            },
            { "islamic.TimePatterns", metaValue_TimePatterns },
            { "MonthAbbreviations", metaValue_MonthNames },
            { "standalone.DayNames", metaValue_DayNames },
            { "vaii.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ua620",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "adlm.NumberElements", metaValue_adlm_NumberElements },
            { "QuarterAbbreviations", metaValue_QuarterNames },
            { "java.time.japanese.narrow.Eras", metaValue_java_time_japanese_narrow_Eras },
            { "field.year", "Year" },
            { "segment.NumberElements", metaValue_adlm_NumberElements },
            { "arabext.NumberElements",
                new String[] {
                    "\u066b",
                    "\u066c",
                    "\u061b",
                    "\u066a",
                    "\u06f0",
                    "#",
                    "\u200e-\u200e",
                    "\u00d7\u06f1\u06f0^",
                    "\u0609",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "japanese.AmPmMarkers", metaValue_AmPmMarkers },
            { "AmPmMarkers", metaValue_AmPmMarkers },
            { "timezone.gmtFormat", "GMT{0}" },
            { "java.time.japanese.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "mtei.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "japanese.narrow.Eras", metaValue_java_time_japanese_narrow_Eras },
            { "takr.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "mymr.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1040",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "japanese.TimePatterns", metaValue_TimePatterns },
            { "narrow.Eras", metaValue_long_Eras },
            { "roc.long.Eras", metaValue_java_time_roc_long_Eras },
            { "timezone.regionFormat.standard", "{0} (+0)" },
            { "DefaultNumberingSystem", "latn" },
            { "tamldec.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0be6",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "japanese.abbreviated.AmPmMarkers", metaValue_AmPmMarkers },
            { "mathmono.NumberElements", metaValue_adlm_NumberElements },
            { "roc.MonthAbbreviations", metaValue_MonthNames },
            { "java.time.islamic.narrow.Eras", metaValue_java_time_islamic_long_Eras },
            { "beng.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u09e6",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "long.Eras", metaValue_long_Eras },
            { "roc.QuarterNarrows", metaValue_QuarterNarrows },
            { "saur.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\ua8d0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "buddhist.MonthNames", metaValue_MonthNames },
            { "gong.NumberElements", metaValue_adlm_NumberElements },
            { "MonthNarrows", metaValue_MonthNarrows },
            { "japanese.DatePatterns", metaValue_buddhist_DatePatterns },
            { "buddhist.DayNames", metaValue_DayNames },
            { "japanese.long.Eras", metaValue_java_time_japanese_long_Eras },
            { "field.era", "Era" },
            { "field.dayperiod", "Dayperiod" },
            { "lana.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "mathsans.NumberElements", metaValue_adlm_NumberElements },
            { "ahom.NumberElements", metaValue_adlm_NumberElements },
            { "diak.NumberElements", metaValue_adlm_NumberElements },
            { "field.month", "Month" },
            { "osma.NumberElements", metaValue_adlm_NumberElements },
            { "java.time.japanese.long.Eras", metaValue_java_time_japanese_long_Eras },
            { "roc.Eras", metaValue_java_time_roc_long_Eras },
            { "DayAbbreviations", metaValue_DayNames },
            { "DayNarrows", metaValue_DayNarrows },
            { "gonm.NumberElements", metaValue_adlm_NumberElements },
            { "guru.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "knda.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0ce6",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "bali.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1b50",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "java.time.roc.narrow.Eras", metaValue_java_time_roc_long_Eras },
            { "sora.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "long.CompactNumberPatterns", metaValue_short_CompactNumberPatterns },
            { "modi.NumberElements", metaValue_adlm_NumberElements },
            { "standalone.QuarterNames", metaValue_QuarterNames },
            { "standalone.DayNarrows", metaValue_DayNarrows },
            { "DateTimePatternChars", "GyMdkHmsSEDFwWahKzZ" },
            { "mlym.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0d66",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "lepc.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1c40",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "gujr.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "fullwide.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\uff10",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "limb.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "java.time.roc.Eras", metaValue_java_time_roc_long_Eras },
            { "khmr.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u17e0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "field.zone", "Zone" },
            { "arab.NumberPatterns",
                new String[] {
                    "#,##0.###",
                    "#,##0.00\u00a0\u00a4",
                    "#,##0%",
                    "",
                }
            },
            { "thai.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0e50",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "buddhist.abbreviated.AmPmMarkers", metaValue_AmPmMarkers },
            { "Eras", metaValue_long_Eras },
            { "hanidec.NumberElements", metaValue_adlm_NumberElements },
            { "lepc.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "islamic.QuarterNames", metaValue_QuarterNames },
            { "mymrshan.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "islamic.abbreviated.AmPmMarkers", metaValue_AmPmMarkers },
            { "nkoo.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "mong.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1810",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "java.time.islamic.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "newa.NumberElements", metaValue_adlm_NumberElements },
            { "field.weekday", "Day of the Week" },
            { "brah.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "java.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "tirh.NumberElements", metaValue_adlm_NumberElements },
            { "thai.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "fullwide.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "japanese.QuarterNames", metaValue_QuarterNames },
            { "telu.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0c66",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "timezone.hourFormat", "+HH:mm;-HH:mm" },
            { "olck.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "japanese.DayNames", metaValue_DayNames },
            { "knda.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "japanese.DayAbbreviations", metaValue_DayNames },
            { "lanatham.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u1a90",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "mathbold.NumberElements", metaValue_adlm_NumberElements },
            { "buddhist.Eras", metaValue_java_time_buddhist_long_Eras },
            { "laoo.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "talu.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "field.week", "Week" },
            { "buddhist.QuarterNames", metaValue_QuarterNames },
            { "sinh.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0de6",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "orya.NumberElements",
                new String[] {
                    ".",
                    ",",
                    ";",
                    "%",
                    "\u0b66",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                    "",
                    "",
                }
            },
            { "java.time.roc.DatePatterns", metaValue_java_time_buddhist_DatePatterns },
            { "rohg.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "short.CompactNumberPatterns", metaValue_short_CompactNumberPatterns },
            { "buddhist.DayAbbreviations", metaValue_DayNames },
            { "cham.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "field.hour", "Hour" },
            { "islamic.MonthAbbreviations",
                new String[] {
                    "Muh.",
                    "Saf.",
                    "Rab. I",
                    "Rab. II",
                    "Jum. I",
                    "Jum. II",
                    "Raj.",
                    "Sha.",
                    "Ram.",
                    "Shaw.",
                    "Dhu\u02bbl-Q.",
                    "Dhu\u02bbl-H.",
                    "",
                }
            },
            { "islamic.narrow.Eras", metaValue_java_time_islamic_long_Eras },
            { "kali.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "standalone.MonthNames", metaValue_MonthNames },
            { "latn.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "java.time.islamic.long.Eras", metaValue_java_time_islamic_long_Eras },
            { "QuarterNames", metaValue_QuarterNames },
            { "roc.TimePatterns", metaValue_TimePatterns },
            { "japanese.Eras", metaValue_java_time_japanese_long_Eras },
            { "deva.NumberPatterns", metaValue_adlm_NumberPatterns },
            { "roc.abbreviated.AmPmMarkers", metaValue_AmPmMarkers },
        };
        return data;
    }
}
