-- =========================================================================
-- Exercise 3: Stored Procedures
-- Scenario 3: TransferFunds stored procedure checking source balance.
-- =========================================================================

-- Enable DBMS_OUTPUT for printing to console
SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_SourceAccountID IN NUMBER,
    p_DestAccountID   IN NUMBER,
    p_Amount          IN NUMBER
) AS
    v_SourceBalance NUMBER;
    v_SourceExists  NUMBER;
    v_DestExists    NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--------------------------------------------------');
    DBMS_OUTPUT.PUT_LINE('Executing transfer of $' || p_Amount || ' from Account ' || p_SourceAccountID || ' to Account ' || p_DestAccountID);
    
    -- 1. Validate negative/zero amount
    IF p_Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Transfer amount must be positive.');
    END IF;

    -- 2. Verify both accounts exist
    SELECT COUNT(*) INTO v_SourceExists FROM Accounts WHERE AccountID = p_SourceAccountID;
    SELECT COUNT(*) INTO v_DestExists FROM Accounts WHERE AccountID = p_DestAccountID;
    
    IF v_SourceExists = 0 OR v_DestExists = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Source or destination Account ID does not exist.');
    END IF;

    -- 3. Retrieve source balance with row-locking
    SELECT Balance INTO v_SourceBalance 
    FROM Accounts 
    WHERE AccountID = p_SourceAccountID 
    FOR UPDATE;
    
    -- 4. Check for sufficient balance
    IF v_SourceBalance < p_Amount THEN
        RAISE_APPLICATION_ERROR(-20003, 'Insufficient balance. Available: $' || v_SourceBalance || ' | Requested: $' || p_Amount);
    END IF;

    -- 5. Perform updates
    UPDATE Accounts
    SET Balance = Balance - p_Amount,
        LastModified = SYSDATE
    WHERE AccountID = p_SourceAccountID;

    UPDATE Accounts
    SET Balance = Balance + p_Amount,
        LastModified = SYSDATE
    WHERE AccountID = p_DestAccountID;

    -- 6. Insert transaction records
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (Transactions_Seq.NEXTVAL, p_SourceAccountID, SYSDATE, p_Amount, 'Withdrawal');

    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (Transactions_Seq.NEXTVAL, p_DestAccountID, SYSDATE, p_Amount, 'Deposit');

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('SUCCESS: Funds transferred successfully.');
    
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Database error. Records not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        -- Outputs the application error code and message raised in the body
        DBMS_OUTPUT.PUT_LINE('ERROR: Transfer failed. Details: ' || SQLERRM);
END TransferFunds;
/

-- Test Cases script block (Copy and run to test)
/*
DECLARE
    v_bal1 NUMBER;
    v_bal2 NUMBER;
BEGIN
    -- Initial balance
    SELECT Balance INTO v_bal1 FROM Accounts WHERE AccountID = 1;
    SELECT Balance INTO v_bal2 FROM Accounts WHERE AccountID = 2;
    DBMS_OUTPUT.PUT_LINE('Initial Balances - Acct 1: $' || v_bal1 || ' | Acct 2: $' || v_bal2);
    
    -- Case 1: Valid Transfer
    TransferFunds(1, 2, 100);
    
    -- Case 2: Insufficient Funds (Will trigger RAISE_APPLICATION_ERROR)
    TransferFunds(1, 2, 20000);
    
    -- Final balance
    SELECT Balance INTO v_bal1 FROM Accounts WHERE AccountID = 1;
    SELECT Balance INTO v_bal2 FROM Accounts WHERE AccountID = 2;
    DBMS_OUTPUT.PUT_LINE('Final Balances - Acct 1: $' || v_bal1 || ' | Acct 2: $' || v_bal2);
END;
/
*/
