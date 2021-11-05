package com.example.composody.data

import com.example.composody.faqsdatabase.FAQ

object CurrentFAQs {
    var listOfFAQs = listOf<FAQ>(
        FAQ(0, "What is a melody?",
            "A combination of notes played in a sequence."),
        FAQ(1, "What to do after I have a melody?",
            "Add in some drum patterns, a bass line or a chord progression."),
        FAQ(2, "Do I need to give Composody credit if I decide to publish a song " +
                "using the generated melody?",
            "You should first check to make sure the melody isn’t too similar to an existing " +
                    "melody or song. If it is unique, feel free to use it. If you do end up writing " +
                    "a popular song, don’t worry, we won’t sue you for the rights. We are happy that " +
                    "we were able to help and if you are feeling kind, please mention us so others " +
                    "know we’re here."),
        FAQ(3,"How do you tie lyrics to the melody?",
            "\'Poetry is a good start. When trying to match up the notes to the words there " +
                    "are endless possibilities! Try a few of these to get started: \n" +
                "- Match each syllable to a note\n" +
                "- Try to add more than one word per note\n" +
                "- Stretch a syllable over the length of two notes\""),
        FAQ(4,"How do I know if my melody is unique and not a copy of an existing " +
                "melody?", "That is a good question. One approach is to make sure that 7 " +
                "notes in a row are not the same as another song. One thing you could try is to hum " +
                "your melody to an audio song identifier. The safest bet is to talk to a music " +
                "professional.")
    )
}