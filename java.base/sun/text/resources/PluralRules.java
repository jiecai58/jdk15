package sun.text.resources;
public final class PluralRules {
    public static final String[][] rulesArray = {
        {"af", "one:n = 1"},
        {"ak", "one:n = 0..1"},
        {"am", "one:i = 0 or n = 1"},
        {"an", "one:n = 1"},
        {"ar", "zero:n = 0;one:n = 1;few:n % 100 = 3..10;many:n % 100 = 11..99;two:n = 2"},
        {"ars", "zero:n = 0;one:n = 1;few:n % 100 = 3..10;many:n % 100 = 11..99;two:n = 2"},
        {"as", "one:i = 0 or n = 1"},
        {"asa", "one:n = 1"},
        {"ast", "one:i = 1 and v = 0"},
        {"az", "one:n = 1"},
        {"be", "one:n % 10 = 1 and n % 100 != 11;few:n % 10 = 2..4 and n % 100 != 12..14;many:n % 10 = 0 or n % 10 = 5..9 or n % 100 = 11..14"},
        {"bem", "one:n = 1"},
        {"bez", "one:n = 1"},
        {"bg", "one:n = 1"},
        {"bho", "one:n = 0..1"},
        {"bn", "one:i = 0 or n = 1"},
        {"br", "one:n % 10 = 1 and n % 100 != 11,71,91;few:n % 10 = 3..4,9 and n % 100 != 10..19,70..79,90..99;many:n != 0 and n % 1000000 = 0;two:n % 10 = 2 and n % 100 != 12,72,92"},
        {"brx", "one:n = 1"},
        {"bs", "one:v = 0 and i % 10 = 1 and i % 100 != 11 or f % 10 = 1 and f % 100 != 11;few:v = 0 and i % 10 = 2..4 and i % 100 != 12..14 or f % 10 = 2..4 and f % 100 != 12..14"},
        {"ca", "one:i = 1 and v = 0"},
        {"ce", "one:n = 1"},
        {"ceb", "one:v = 0 and i = 1,2,3 or v = 0 and i % 10 != 4,6,9 or v != 0 and f % 10 != 4,6,9"},
        {"cgg", "one:n = 1"},
        {"chr", "one:n = 1"},
        {"ckb", "one:n = 1"},
        {"cs", "one:i = 1 and v = 0;few:i = 2..4 and v = 0;many:v != 0"},
        {"cy", "zero:n = 0;one:n = 1;few:n = 3;many:n = 6;two:n = 2"},
        {"da", "one:n = 1 or t != 0 and i = 0,1"},
        {"de", "one:i = 1 and v = 0"},
        {"dsb", "one:v = 0 and i % 100 = 1 or f % 100 = 1;few:v = 0 and i % 100 = 3..4 or f % 100 = 3..4;two:v = 0 and i % 100 = 2 or f % 100 = 2"},
        {"dv", "one:n = 1"},
        {"ee", "one:n = 1"},
        {"el", "one:n = 1"},
        {"en", "one:i = 1 and v = 0"},
        {"eo", "one:n = 1"},
        {"es", "one:n = 1"},
        {"et", "one:i = 1 and v = 0"},
        {"eu", "one:n = 1"},
        {"fa", "one:i = 0 or n = 1"},
        {"ff", "one:i = 0,1"},
        {"fi", "one:i = 1 and v = 0"},
        {"fil", "one:v = 0 and i = 1,2,3 or v = 0 and i % 10 != 4,6,9 or v != 0 and f % 10 != 4,6,9"},
        {"fo", "one:n = 1"},
        {"fr", "one:i = 0,1"},
        {"fur", "one:n = 1"},
        {"fy", "one:i = 1 and v = 0"},
        {"ga", "one:n = 1;few:n = 3..6;many:n = 7..10;two:n = 2"},
        {"gd", "one:n = 1,11;few:n = 3..10,13..19;two:n = 2,12"},
        {"gl", "one:i = 1 and v = 0"},
        {"gsw", "one:n = 1"},
        {"gu", "one:i = 0 or n = 1"},
        {"guw", "one:n = 0..1"},
        {"gv", "one:v = 0 and i % 10 = 1;few:v = 0 and i % 100 = 0,20,40,60,80;many:v != 0;two:v = 0 and i % 10 = 2"},
        {"ha", "one:n = 1"},
        {"haw", "one:n = 1"},
        {"he", "one:i = 1 and v = 0;many:v = 0 and n != 0..10 and n % 10 = 0;two:i = 2 and v = 0"},
        {"hi", "one:i = 0 or n = 1"},
        {"hr", "one:v = 0 and i % 10 = 1 and i % 100 != 11 or f % 10 = 1 and f % 100 != 11;few:v = 0 and i % 10 = 2..4 and i % 100 != 12..14 or f % 10 = 2..4 and f % 100 != 12..14"},
        {"hsb", "one:v = 0 and i % 100 = 1 or f % 100 = 1;few:v = 0 and i % 100 = 3..4 or f % 100 = 3..4;two:v = 0 and i % 100 = 2 or f % 100 = 2"},
        {"hu", "one:n = 1"},
        {"hy", "one:i = 0,1"},
        {"ia", "one:i = 1 and v = 0"},
        {"io", "one:i = 1 and v = 0"},
        {"is", "one:t = 0 and i % 10 = 1 and i % 100 != 11 or t != 0"},
        {"it", "one:i = 1 and v = 0"},
        {"iu", "one:n = 1;two:n = 2"},
        {"iw", "one:i = 1 and v = 0;many:v = 0 and n != 0..10 and n % 10 = 0;two:i = 2 and v = 0"},
        {"jgo", "one:n = 1"},
        {"ji", "one:i = 1 and v = 0"},
        {"jmc", "one:n = 1"},
        {"ka", "one:n = 1"},
        {"kab", "one:i = 0,1"},
        {"kaj", "one:n = 1"},
        {"kcg", "one:n = 1"},
        {"kk", "one:n = 1"},
        {"kkj", "one:n = 1"},
        {"kl", "one:n = 1"},
        {"kn", "one:i = 0 or n = 1"},
        {"ks", "one:n = 1"},
        {"ksb", "one:n = 1"},
        {"ksh", "zero:n = 0;one:n = 1"},
        {"ku", "one:n = 1"},
        {"kw", "zero:n = 0;one:n = 1;few:n % 100 = 3,23,43,63,83;many:n != 1 and n % 100 = 1,21,41,61,81;two:n % 100 = 2,22,42,62,82 or n % 1000 = 0 and n % 100000 = 1000..20000,40000,60000,80000 or n != 0 and n % 1000000 = 100000"},
        {"ky", "one:n = 1"},
        {"lag", "zero:n = 0;one:i = 0,1 and n != 0"},
        {"lb", "one:n = 1"},
        {"lg", "one:n = 1"},
        {"ln", "one:n = 0..1"},
        {"lt", "one:n % 10 = 1 and n % 100 != 11..19;few:n % 10 = 2..9 and n % 100 != 11..19;many:f != 0"},
        {"lv", "zero:n % 10 = 0 or n % 100 = 11..19 or v = 2 and f % 100 = 11..19;one:n % 10 = 1 and n % 100 != 11 or v = 2 and f % 10 = 1 and f % 100 != 11 or v != 2 and f % 10 = 1"},
        {"mas", "one:n = 1"},
        {"mg", "one:n = 0..1"},
        {"mgo", "one:n = 1"},
        {"mk", "one:v = 0 and i % 10 = 1 and i % 100 != 11 or f % 10 = 1 and f % 100 != 11"},
        {"ml", "one:n = 1"},
        {"mn", "one:n = 1"},
        {"mo", "one:i = 1 and v = 0;few:v != 0 or n = 0 or n % 100 = 2..19"},
        {"mr", "one:n = 1"},
        {"mt", "one:n = 1;few:n = 0 or n % 100 = 2..10;many:n % 100 = 11..19"},
        {"nah", "one:n = 1"},
        {"naq", "one:n = 1;two:n = 2"},
        {"nb", "one:n = 1"},
        {"nd", "one:n = 1"},
        {"ne", "one:n = 1"},
        {"nl", "one:i = 1 and v = 0"},
        {"nn", "one:n = 1"},
        {"nnh", "one:n = 1"},
        {"no", "one:n = 1"},
        {"nr", "one:n = 1"},
        {"nso", "one:n = 0..1"},
        {"ny", "one:n = 1"},
        {"nyn", "one:n = 1"},
        {"om", "one:n = 1"},
        {"or", "one:n = 1"},
        {"os", "one:n = 1"},
        {"pa", "one:n = 0..1"},
        {"pap", "one:n = 1"},
        {"pcm", "one:i = 0 or n = 1"},
        {"pl", "one:i = 1 and v = 0;few:v = 0 and i % 10 = 2..4 and i % 100 != 12..14;many:v = 0 and i != 1 and i % 10 = 0..1 or v = 0 and i % 10 = 5..9 or v = 0 and i % 100 = 12..14"},
        {"prg", "zero:n % 10 = 0 or n % 100 = 11..19 or v = 2 and f % 100 = 11..19;one:n % 10 = 1 and n % 100 != 11 or v = 2 and f % 10 = 1 and f % 100 != 11 or v != 2 and f % 10 = 1"},
        {"ps", "one:n = 1"},
        {"pt", "one:i = 0..1"},
        {"pt_PT", "one:i = 1 and v = 0"},
        {"rm", "one:n = 1"},
        {"ro", "one:i = 1 and v = 0;few:v != 0 or n = 0 or n % 100 = 2..19"},
        {"rof", "one:n = 1"},
        {"ru", "one:v = 0 and i % 10 = 1 and i % 100 != 11;few:v = 0 and i % 10 = 2..4 and i % 100 != 12..14;many:v = 0 and i % 10 = 0 or v = 0 and i % 10 = 5..9 or v = 0 and i % 100 = 11..14"},
        {"rwk", "one:n = 1"},
        {"saq", "one:n = 1"},
        {"sat", "one:n = 1;two:n = 2"},
        {"sc", "one:i = 1 and v = 0"},
        {"scn", "one:i = 1 and v = 0"},
        {"sd", "one:n = 1"},
        {"sdh", "one:n = 1"},
        {"se", "one:n = 1;two:n = 2"},
        {"seh", "one:n = 1"},
        {"sh", "one:v = 0 and i % 10 = 1 and i % 100 != 11 or f % 10 = 1 and f % 100 != 11;few:v = 0 and i % 10 = 2..4 and i % 100 != 12..14 or f % 10 = 2..4 and f % 100 != 12..14"},
        {"shi", "one:i = 0 or n = 1;few:n = 2..10"},
        {"si", "one:n = 0,1 or i = 0 and f = 1"},
        {"sk", "one:i = 1 and v = 0;few:i = 2..4 and v = 0;many:v != 0"},
        {"sl", "one:v = 0 and i % 100 = 1;few:v = 0 and i % 100 = 3..4 or v != 0;two:v = 0 and i % 100 = 2"},
        {"sma", "one:n = 1;two:n = 2"},
        {"smi", "one:n = 1;two:n = 2"},
        {"smj", "one:n = 1;two:n = 2"},
        {"smn", "one:n = 1;two:n = 2"},
        {"sms", "one:n = 1;two:n = 2"},
        {"sn", "one:n = 1"},
        {"so", "one:n = 1"},
        {"sq", "one:n = 1"},
        {"sr", "one:v = 0 and i % 10 = 1 and i % 100 != 11 or f % 10 = 1 and f % 100 != 11;few:v = 0 and i % 10 = 2..4 and i % 100 != 12..14 or f % 10 = 2..4 and f % 100 != 12..14"},
        {"ss", "one:n = 1"},
        {"ssy", "one:n = 1"},
        {"st", "one:n = 1"},
        {"sv", "one:i = 1 and v = 0"},
        {"sw", "one:i = 1 and v = 0"},
        {"syr", "one:n = 1"},
        {"ta", "one:n = 1"},
        {"te", "one:n = 1"},
        {"teo", "one:n = 1"},
        {"ti", "one:n = 0..1"},
        {"tig", "one:n = 1"},
        {"tk", "one:n = 1"},
        {"tl", "one:v = 0 and i = 1,2,3 or v = 0 and i % 10 != 4,6,9 or v != 0 and f % 10 != 4,6,9"},
        {"tn", "one:n = 1"},
        {"tr", "one:n = 1"},
        {"ts", "one:n = 1"},
        {"tzm", "one:n = 0..1 or n = 11..99"},
        {"ug", "one:n = 1"},
        {"uk", "one:v = 0 and i % 10 = 1 and i % 100 != 11;few:v = 0 and i % 10 = 2..4 and i % 100 != 12..14;many:v = 0 and i % 10 = 0 or v = 0 and i % 10 = 5..9 or v = 0 and i % 100 = 11..14"},
        {"ur", "one:i = 1 and v = 0"},
        {"uz", "one:n = 1"},
        {"ve", "one:n = 1"},
        {"vo", "one:n = 1"},
        {"vun", "one:n = 1"},
        {"wa", "one:n = 0..1"},
        {"wae", "one:n = 1"},
        {"xh", "one:n = 1"},
        {"xog", "one:n = 1"},
        {"yi", "one:i = 1 and v = 0"},
        {"zu", "one:i = 0 or n = 1"},
    };
}
