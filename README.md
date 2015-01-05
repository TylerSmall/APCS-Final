APCS-Final
==========

# WebCrawler
- crawl(url)
  - should search the csv file for a matching url
  - should not get the same page twice
  - should take the url param and get the pae
  - should parse out the page title
  - should parse out all links
  - should write the url to the csv file
  - should write the title to the csv file
  - should escape the title if necessary
  - should call itself for each link found on the page

# SearchEngine
- search(query, url)
  - should check if query is url
    - should search csv file for url
  - should search titles and urls for instances of query
  - should return pages where keywords appear
