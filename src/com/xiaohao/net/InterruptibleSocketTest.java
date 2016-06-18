package com.xiaohao.net;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InterruptibleSocketTest {

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        JFrame frame = new InterruptibleSocketFrame();
        frame.setTitle("InterruptibleSocketTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
      }
    });
  }

}

class InterruptibleSocketFrame extends JFrame {
  public static final int TEXT_ROWS = 20;
  public static final int TEXT_COLUMNS = 20;

  private Scanner in;
  private JButton interruptibleButton;
  private JButton blockingButton;
  private JButton cancelButton;
  private JButton closeButton;
  private JTextArea messages;
  private Thread connectThread;
  private TestServer server;
  private Thread serverThread;
  
  public InterruptibleSocketFrame() {
    JPanel northPanel = new JPanel();
    add(northPanel, BorderLayout.NORTH);
    
    messages  = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
    add(new JScrollPane(messages));
    
    interruptibleButton = new JButton("Interruptible");
    blockingButton = new JButton("Blocking");
    cancelButton = new JButton("Cancel");
    closeButton = new JButton("Close");
    northPanel.add(interruptibleButton);
    northPanel.add(blockingButton);
    northPanel.add(cancelButton);
    northPanel.add(closeButton);
    
    interruptibleButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        interruptibleButton.setEnabled(false);
        blockingButton.setEnabled(false);
        cancelButton.setEnabled(true);
        
        connectThread = new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              connectInterruptibly();
            } catch (Exception e) {
              messages.append("\nconnectInterruptibly run: " + e);
            }
          }
        });
        connectThread.start();
      }
    });

    blockingButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        interruptibleButton.setEnabled(false);
        blockingButton.setEnabled(false);
        cancelButton.setEnabled(true);
        
        connectThread = new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              connectBlocking();
            } catch (Exception e) {
              messages.append("\nconnectBlocking run: " + e);
            }
          }
        });
        connectThread.start();
      }
    });
    
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        connectThread.interrupt();
        cancelButton.setEnabled(false);
      }
    });
    
    closeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        serverThread.interrupt();
        closeButton.setEnabled(false);
      }
    });
    
    server = new TestServer();
    serverThread = new Thread(server);
    serverThread.start();
    pack();
  }
  
  public void connectInterruptibly() throws IOException {
    messages.append("Interruptible:\n");
    try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8189))) {
      in = new Scanner(channel);
      while (!Thread.currentThread().isInterrupted()) {
        messages.append("Reading ");
        if (in.hasNext()) {
          String line = in.nextLine();
          messages.append(line + "\n");
        }
      }
    } finally {
      EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
          messages.append("Channel closed\n");
          interruptibleButton.setEnabled(true);
          blockingButton.setEnabled(true);
        }
      });
    }
  }
  
  public void connectBlocking() throws Exception {
    messages.append("Blocking:\n");
    try (Socket socket = new Socket("localhost", 8189)) {
      in = new Scanner(socket.getInputStream());
      while (!Thread.currentThread().isInterrupted()) {
        messages.append("Reading ");
        if (in.hasNext()) {
          String line = in.nextLine();
          messages.append(line + "\n");
        }
      }
    } finally {
      EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
          messages.append("Socket closed\n");
          interruptibleButton.setEnabled(true);
          blockingButton.setEnabled(true);
        }
      });
    }
  }

  class TestServer implements Runnable {

    @Override
    public void run() {
      ServerSocket s = null;
      try {
        s = new ServerSocket(8189);
        while (!Thread.currentThread().isInterrupted()) {
          Socket incoming = s.accept();
          TestServerHandler handler = new TestServerHandler(incoming);
          Thread t = new Thread(handler);
          t.start();
          messages.append("TestServer accept new conncetion\n");
        }
      } catch (Throwable t) {
        messages.append("\nTestServer run: " + t);
      } finally {
        try {
          if (s != null) {
            s.close();
          }
        } catch (IOException e) {
        }
      }
    }
  }

  class TestServerHandler implements Runnable {
    private Socket incoming;
    private int counter;

    public TestServerHandler(Socket incoming) {
      this.incoming = incoming;
    }

    @Override
    public void run() {
      try {
        try {
          OutputStream output = incoming.getOutputStream();
          PrintWriter out = new PrintWriter(output, true);

          counter = 0;
          while (counter < 100) {
            if (counter < 10) {
              out.println(counter);
              counter++;
              Thread.sleep(100);
            }
          }
        } finally {
          incoming.close();
          messages.append("Closing server\n");
        }
      } catch (Exception e) {
        messages.append("\nTestServerHandler run: " + e);
      }
    }
  }
}