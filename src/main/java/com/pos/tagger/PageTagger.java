package com.pos.tagger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/**
 * @author Abhijith Nagaraja
 */
public class PageTagger
{
	private static final String DEFAULT_TAGGER = "taggers/english-left3words-distsim.tagger";

	//Maxent Tagger Instance
	private MaxentTagger tagger;

	/**
	 * Default Constructor. Initializes tagger to default tagger
	 */
	public PageTagger()
	{
		this( DEFAULT_TAGGER );
	}

	/**
	 * This constructor takea a taggerModel file path as an argument.
	 * @param taggerModel
	 */
	public PageTagger( String taggerModel )
	{
		// Initialize the tagger
		setTagger( new MaxentTagger( taggerModel ) );
	}

	/**
	 * @return tagger instance
	 */
	public MaxentTagger getTagger()
	{
		return tagger;
	}

	/**
	 * Set the tagger instance
	 * @param tagger
	 */
	public void setTagger( MaxentTagger tagger )
	{
		this.tagger = tagger;
	}

	/**
	 * Tags the given text and returns it.
	 * To avoid out of memory issue, given String tagged in a batch of 1000 words. A Word is defined as the sequence of characters without space character  
	 * @param textToTag
	 * @return tagged String
	 */
	public String tagText( String textToTag )
	{
		//A string is split with Space character
		String words[] = textToTag.split( " " );

		// The tagged string
		String tagged = "";
		int wordsLength = words.length;

		//Tagging is done in a batch of 1000 words
		for ( int i = 0; i < wordsLength; i += 1000 )
		{
			String toTag = "";
			for ( int j = 0; j < 1000 && ( i + j ) < wordsLength; j++ )
			{
				toTag += words[i + j] + " ";
			}
			//tagString method is called
			tagged += getTagger().tagString( toTag );
		}
		return tagged;
	}

	/**
	 * Uses a 3rd party library called Jsoup to extract the html.
	 * @param url
	 * @return the extracted text from the html
	 */
	public String getText( URL url )
	{
		String result = null;
		try
		{
			Document doc = Jsoup.parse( url, 3000 );
			result = doc.body().text();
		}
		catch ( IOException exception )
		{
			exception.printStackTrace();
		}
		return result;
	}

	public static void main( String[] args )
	{
		//Check for the given input
		if ( args.length > 0 )
		{
			try
			{
				URL url = new URL( args[0] );
				PageTagger pageTagger = new PageTagger();
				System.out.println( pageTagger.tagText( pageTagger.getText( url ) ) );
			}
			catch ( MalformedURLException e )
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println( "Provide the URL as argument" );
		}
	}
}
