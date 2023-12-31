/*
 * Copyright 2005 Ralf Joachim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.castor.transactionmanager;

/**
 * Dummy transaction manager factory to test AbstractTransactionManagerFactory.
 *  
 * @author <a href="mailto:ralf DOT joachim AT syscon DOT eu">Ralf Joachim</a>
 * @version $Revision: 6907 $ $Date: 2006-04-29 05:45:43 -0600 (Sat, 29 Apr 2006) $
 * @since 1.0
 */
public final class TransactionManagerFactoryDummy
extends AbstractTransactionManagerFactory {
    //--------------------------------------------------------------------------

    /** Name of the IBM Websphere mock to test AbstractTransactionManagerFactory. */
    public static final String FACTORY_CLASS_NAME = WebSphereMock.class.getName();
    
    /** Name of the method that is used upon the factory to have a TransactionManager
     *  instance created. */
    public static final String FACTORY_METHOD_NAME = "getTransactionManager";
    
    /** The name of the factory. */
    public static final String NAME = "dummy";

    //--------------------------------------------------------------------------

    /**
     * @see org.exolab.castor.jdo.transactionmanager.spi.AbstractTransactionManagerFactory
     *      #getFactoryClassName()
     */
    public String getFactoryClassName() { return FACTORY_CLASS_NAME; }
    
    /**
     * @see org.exolab.castor.jdo.transactionmanager.spi.AbstractTransactionManagerFactory
     *      #getFactoryMethodName()
     */
    public String getFactoryMethodName() { return FACTORY_METHOD_NAME; }
    
    /**
     * @see org.exolab.castor.jdo.transactionmanager.TransactionManagerFactory#getName()
     */
    public String getName() { return NAME; }

    //--------------------------------------------------------------------------
}
