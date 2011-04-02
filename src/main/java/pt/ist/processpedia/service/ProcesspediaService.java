package pt.ist.processpedia.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.processpedia.domain.Processpedia;
import pt.ist.processpedia.domain.exception.ProcesspediaDomainException;

import pt.ist.processpedia.service.exception.ProcesspediaServiceException;

public abstract class ProcesspediaService<T> {
  
  public abstract T dispatch() throws ProcesspediaServiceException;
  
  public T execute() {
    T result = null;
    Transaction.begin();
    try {
      result = dispatch();
    } catch(ProcesspediaServiceException e) {
      Transaction.abort();
      throw e;
    }
    Transaction.commit();
    return result;
  }
  
  public final Processpedia getProcesspedia() {
    return FenixFramework.getRoot();
  }
  
}