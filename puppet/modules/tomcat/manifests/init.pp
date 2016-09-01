class tomcat {

  package { ["openjdk-7-jre","tomcat7",]:
    ensure  => present,
    require => Class["system-update"],
  }

  file {"/var/lib/tomcat7/webapps/system":
  	ensure => "link",
  	target => "/vagrant/system/WebContent",
  	require => Package["tomcat7"],
  	notify => Service["tomcat7"],
  }

  service { "tomcat7":
    ensure  => "running",
    require => Package["tomcat7"],
  }
}
