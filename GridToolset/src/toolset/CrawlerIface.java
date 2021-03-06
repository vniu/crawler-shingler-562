package toolset;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import crawler.Crawler;
import crawler.PageLW;

public class CrawlerIface {
	/**
	 * Crawler Planning
	 * 
	 * POOLS: URLPOOL - urls to be downloaded SEENPOOL - urls already seen, do
	 * not re-queue these
	 * 
	 * @throws MalformedURLException
	 * 
	 * */

	public static void startCrawler(String storePath, String seed,
			String urlPoolFile, int maxDepth, int threads, int maxPagesPerCrawl)
			throws MalformedURLException {
		
		final int maxDomainPerCrawl = 10;

		Crawler crawler = new Crawler(seed, storePath, null, true,
				true, maxDomainPerCrawl, null,maxPagesPerCrawl);
		
		PrintWriter writer = null;
		crawler.crawl(maxDepth, threads);

		
		try {
			writer = new PrintWriter(urlPoolFile, "UTF-8");
			for (String s : crawler.uniqueDomains)
				writer.println(s);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
		}

	}
}
