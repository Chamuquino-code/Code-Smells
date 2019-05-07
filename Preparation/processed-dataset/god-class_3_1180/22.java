/**
   * Receive notification of the beginning of the document.
   *
   * <p>By default, do nothing.  Application writers may override this
   * method in a subclass to take specific actions at the beginning
   * of a document (such as allocating the root node of a tree or
   * creating an output file).</p>
   *
   * @throws org.xml.sax.SAXException Any SAX exception, possibly
   *            wrapping another exception.
   * @see org.xml.sax.ContentHandler#startDocument
   *
   * @throws SAXException
   */
public void startDocument() throws SAXException {
    try {
        if (null == m_resultContentHandler)
            createResultContentHandler(m_result);
    } catch (TransformerException te) {
        throw new SAXException(te.getMessage(), te);
    }
    // Reset for multiple transforms with this transformer.  
    m_flushedStartDoc = false;
    m_foundFirstElement = false;
}
